package com.example.todotimer.di.screens.main

import com.example.domain.common.core.utils.Mapper
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.main.entity.TodoUiEntity
import com.example.todotimer.screens.main.mapper.TodoMapper
import dagger.Module
import dagger.Provides

@Module
object MainModule {

    @Provides
    fun provideMapper(): Mapper<TodoData, TodoUiEntity> = TodoMapper()
}
