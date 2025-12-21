package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.usecase.GetAllBookUseCase
import com.example.myapplication.domain.usecase.GetAllCategoryUseCase
import com.example.myapplication.presentation.model.ResultState
import com.example.myapplication.presentation.model.SelectedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListBookViewModel @Inject constructor(
    private val getAllBookUseCase: GetAllBookUseCase,
    private val getAllCategoryUseCase: GetAllCategoryUseCase
) : ViewModel() {

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Init)

    val resultState : StateFlow<ResultState> = _resultState.asStateFlow()

    private val _books = MutableLiveData<List<Book>>(emptyList())

    val books : LiveData<List<Book>> get() = _books

    private var _bookData: List<Book> = emptyList()

    private val _category = MutableLiveData<List<SelectedItem<Category>>>(emptyList())

    val category : LiveData<List<SelectedItem<Category>>> get() = _category

    private val _textSearch = MutableStateFlow("")

    val textSearch : StateFlow<String> = _textSearch.asStateFlow()

    fun updateTextSearch(textSearch: String){
        _textSearch.value = textSearch
    }

    fun filterBook(){
        _books.value = _bookData
            .filterBySearchText()
            .filterByCategory()
    }

    private fun List<Book>.filterBySearchText() : List<Book>{

        if(_textSearch.value.isBlank())
            return this;

        return this.filter { book ->
            book.name.contains(_textSearch.value)
                    || book.description.contains(_textSearch.value)
        }

    }

    private fun List<Book>.filterByCategory() : List<Book>{

        _category.value?.let {
            if(!_category.value!!.any { item -> item.isSelected.value }){
                return this;
            }
        }

        val selectedIds : List<String> = _category.value?.filter {
            item -> item.isSelected.value
        }?.map { item -> item.item.id } ?: emptyList()

        return this.filter { book -> selectedIds.contains(book.category) }
    }

    init {
        refresh()
    }

    fun refresh(){
        loadAllCategory()
        loadAllBook()
    }

    private fun loadAllCategory(){
        _resultState.value = ResultState.Loading

        viewModelScope.launch {
            when(val result = getAllCategoryUseCase()){
                is CustomResult.Failure -> {

                    _resultState.value = ResultState.Error(result.message)

                }
                is CustomResult.Success<List<Category>> -> {

                    _category.value = result.value
                        .map { category -> SelectedItem(category) }

                    _resultState.value = ResultState.Init
                }
            }
        }

    }

    private fun loadAllBook(){
        _resultState.value = ResultState.Loading

        viewModelScope.launch {
            when(val result = getAllBookUseCase()){
                is CustomResult.Failure -> {

                    _resultState.value = ResultState.Error(result.message)

                }
                is CustomResult.Success<List<Book>> -> {

                    _bookData = result.value

                    _books.value = _bookData

                    _resultState.value = ResultState.Init
                }
            }
        }

    }
}