package com.example.todotimer.screens.main.ui.views

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.todotimer.screens.main.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.screens.common.entity.TodoUiEntity

@Composable
fun TodoItems(
    context: Context,
    todoItems: List<TodoUiEntity>,
    viewModel: MainViewModel = viewModel()
) {

    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(15.dp, 15.dp, 15.dp, 0.dp)
    ) {
        items(todoItems) { todoItem ->
            viewModel.onListScrolled(lazyListState.firstVisibleItemScrollOffset)
            TodoItem(context, todoItem)
        }
    }
}
