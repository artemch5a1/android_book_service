package com.example.myapplication.presentation.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedItem<T>(
    val item:T
) {

    private val _isSelected = MutableStateFlow(false)

    val isSelected : StateFlow<Boolean> = _isSelected.asStateFlow()

    fun updateSelected(isSelected: Boolean){
        _isSelected.value = isSelected
    }
}