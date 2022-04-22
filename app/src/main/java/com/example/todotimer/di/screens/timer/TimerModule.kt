package com.example.todotimer.di.screens.timer

import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.common.core.utils.Mapper
import com.example.domain.interactor.DeleteTodoUseCase
import com.example.domain.interactor.GetTodoByIdUseCase
import com.example.domain.interactor.UpdateTodoUseCase
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import com.example.todotimer.screens.common.mapper.TodoMapper
import com.example.todotimer.screens.timer.viewmodel.TimerViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object TimerModule {

    @Provides
    fun provideTimerViewModelFactory(
        getTodoByIdUseCase: GetTodoByIdUseCase,
        deleteTodoUseCase: DeleteTodoUseCase,
        updateTodoUseCase: UpdateTodoUseCase,
        mapper: Mapper<TodoData, TodoUiEntity>,
        timeFormatService: TimeFormatService
    ): TimerViewModelFactory {
        return TimerViewModelFactory(
            getTodoByIdUseCase,
            deleteTodoUseCase,
            updateTodoUseCase,
            mapper,
            timeFormatService
        )
    }
}
