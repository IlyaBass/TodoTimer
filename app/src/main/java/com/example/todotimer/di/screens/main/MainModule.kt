package com.example.todotimer.di.screens.main

import com.example.domain.common.core.utils.Mapper
import com.example.domain.interactor.AddTodoUseCase
import com.example.domain.interactor.DeleteTodoUseCase
import com.example.domain.interactor.GetTodosUseCase
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import com.example.todotimer.screens.main.mapper.TodoMapper
import com.example.todotimer.screens.main.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object MainModule {

    @Provides
    fun provideMapper(): Mapper<TodoData, TodoUiEntity> = TodoMapper()

    @Provides
    fun provideMainViewModelFactory(
        mapper: Mapper<TodoData, TodoUiEntity>,
        getTodosUseCase: GetTodosUseCase,
        addTodoUseCase: AddTodoUseCase,
        deleteTodoUseCase: DeleteTodoUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(mapper, getTodosUseCase, addTodoUseCase, deleteTodoUseCase)
    }
}
