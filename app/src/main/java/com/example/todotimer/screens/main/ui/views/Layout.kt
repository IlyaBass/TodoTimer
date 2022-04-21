package com.example.todotimer.screens.main.ui.views

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.todotimer.screens.main.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Layout(
    context: Context,
    viewModel: MainViewModel = viewModel()
) {
    val todoList = viewModel.todoList.collectAsState().value
    TodoItems(context = context, todoItems = todoList)
}

