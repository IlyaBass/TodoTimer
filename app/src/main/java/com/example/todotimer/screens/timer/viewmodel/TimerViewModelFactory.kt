package com.example.todotimer.screens.timer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.common.core.utils.Mapper
import com.example.domain.interactor.DeleteTodoUseCase
import com.example.domain.interactor.GetTodoByIdUseCase
import com.example.domain.interactor.UpdateTodoUseCase
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import java.lang.IllegalArgumentException

class TimerViewModelFactory(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val mapper: Mapper<TodoData, TodoUiEntity>,
    private val timeFormatService: TimeFormatService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return TimerViewModel(
                getTodoByIdUseCase = getTodoByIdUseCase,
                deleteTodoUseCase = deleteTodoUseCase,
                updateTodoUseCase = updateTodoUseCase,
                mapper = mapper,
                timeFormatService = timeFormatService
            ) as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}