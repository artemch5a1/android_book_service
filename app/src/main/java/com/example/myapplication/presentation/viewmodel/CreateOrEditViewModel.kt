package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.AppSession
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.model.LoginRequest
import com.example.myapplication.domain.usecase.CreateBookUseCase
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
class CreateOrEditViewModel @Inject constructor(
    private val createBookUseCase: CreateBookUseCase,
    private val appSession: AppSession,
    private val getAllCategoryUseCase: GetAllCategoryUseCase
) : ViewModel() {


    private val _resultState = MutableStateFlow<ResultState>(ResultState.Init)

    val resultState : StateFlow<ResultState> = _resultState.asStateFlow()

    private val _book = MutableStateFlow<Book>(Book.createBook(
        appSession.currentLogin?.loginDetail?.id ?: ""
    ))

    val book : StateFlow<Book> = _book.asStateFlow()

    private val _category = MutableLiveData<List<Category>>(emptyList())

    val category : LiveData<List<Category>> get() = _category

    fun updateLogin(book: Book){
        _book.value = book
    }

    fun execute(){
        createBook()
    }

    init {
        loadAllCategory()
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

                    _resultState.value = ResultState.Init
                }
            }
        }

    }

    private fun createBook(){
        _resultState.value = ResultState.Loading

        viewModelScope.launch {

            when(val result = createBookUseCase(_book.value)){
                is CustomResult.Failure -> {

                    _resultState.value = ResultState.Error(result.message)

                }
                is CustomResult.Success<Unit> -> {

                    _resultState.value = ResultState.Success()

                }
            }

        }

    }
}