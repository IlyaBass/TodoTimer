package com.example.todotimer.di.interactor

import com.example.domain.interactor.*
import com.example.domain.repo.todo.TodoRepo
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
object InteractorModule {

    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun provideGetTodosUseCase(
        repo: TodoRepo,
        uiScheduler: Scheduler
    ): GetTodosUseCase = GetTodosUseCase(repo, uiScheduler)

    @Provides
    fun provideAddTodoUseCase(
        repo: TodoRepo,
        uiScheduler: Scheduler
    ): AddTodoUseCase = AddTodoUseCase(repo, uiScheduler)

    @Provides
    fun provideClearTodoUseCase(
        repo: TodoRepo,
        uiScheduler: Scheduler
    ): DeleteTodoUseCase = DeleteTodoUseCase(repo, uiScheduler)

    @Provides
    fun provideGetTodoByIdUseCase(
        repo: TodoRepo,
        uiScheduler: Scheduler
    ): GetTodoByIdUseCase = GetTodoByIdUseCase(repo, uiScheduler)

    @Provides
    fun provideUpdateTodoUseCase(
        repo: TodoRepo,
        uiScheduler: Scheduler
    ): UpdateTodoUseCase = UpdateTodoUseCase(repo, uiScheduler)

    @Provides
    fun provideObserveTodoByIdUseCase(
        repo: TodoRepo,
        uiScheduler: Scheduler
    ): ObserveTodoByIdUseCase = ObserveTodoByIdUseCase(repo, uiScheduler)
}
