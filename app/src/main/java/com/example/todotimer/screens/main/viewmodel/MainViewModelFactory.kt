package com.example.todotimer.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.common.core.utils.Mapper
import com.example.domain.interactor.AddTodoUseCase
import com.example.domain.interactor.DeleteTodoUseCase
import com.example.domain.interactor.GetTodosUseCase
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    private val mapper: Mapper<TodoData, TodoUiEntity>,
    private val getTodosUseCase: GetTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                mapper = mapper,
                getTodosUseCase = getTodosUseCase,
                addTodoUseCase = addTodoUseCase,
                deleteTodoUseCase = deleteTodoUseCase,
            ) as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}
