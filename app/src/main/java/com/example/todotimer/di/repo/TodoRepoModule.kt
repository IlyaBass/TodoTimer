package com.example.todotimer.di.repo

import com.example.data.repo.todo.DefaultTodoRepo
import com.example.data.repo.todo.TodoStorage
import com.example.domain.common.core.utils.DbConverter
import com.example.domain.repo.todo.TodoRepo
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.common.database.TodoDatabase
import com.example.todotimer.repo.todo.TodoDatabaseStorage
import com.example.todotimer.repo.todo.database.converter.TodoConverter
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TodoRepoModule {

    @Provides
    @Singleton
    internal fun provideRepo(
        storage: TodoStorage
    ): TodoRepo = DefaultTodoRepo(storage)

    @Provides
    internal fun provideStorage(
        todoDatabase: TodoDatabase,
        todoConverter: DbConverter<TodoData, TodoDatabaseEntity>
    ): TodoStorage = TodoDatabaseStorage(
        todoDatabase.todoDao(),
        todoConverter
    )

    @Provides
    internal fun provideConverter(): DbConverter<TodoData, TodoDatabaseEntity> = TodoConverter()
}
