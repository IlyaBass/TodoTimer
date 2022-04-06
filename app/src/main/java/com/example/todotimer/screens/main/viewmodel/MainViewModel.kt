package com.example.todotimer.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

    private val _todoList = MutableStateFlow(listOf(
        "Need to wash the dishes",
        "Learn about fragments and activities",
        "Make my own cool application",
        "Need to wash the dishes",
        "Learn about fragments and activities",
        "Make my own cool application",
        "Need to wash the dishes",
        "Learn about fragments and activities",
        "Make my own cool application",
        "Need to wash the dishes",
        "Learn about fragments and activities",
        "Make my own cool application"
    ))
    val todoList = _todoList.asStateFlow()

    private val _isFloatingBtnVisible = MutableStateFlow(1F)
    val isFloatingBtnVisible = _isFloatingBtnVisible.asStateFlow()

    private val _dialogTodoTitle = MutableStateFlow("")
    val dialogTodoTitle = _dialogTodoTitle.asStateFlow()

    private val _isDialogVisible = MutableStateFlow(false)
    val isDialogVisible = _isDialogVisible.asStateFlow()

    fun onListScrolled(firstVisibleItemOffset: Int) {
        if (firstVisibleItemOffset > 0) {
            _isFloatingBtnVisible.value = 0F
        } else {
            _isFloatingBtnVisible.value = 1F
        }
    }

    fun changeTodoTitle(text: String) {
        _dialogTodoTitle.value = text
    }

    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun hideDialog() {
        _isDialogVisible.value = false
        _dialogTodoTitle.value = ""
    }
}
