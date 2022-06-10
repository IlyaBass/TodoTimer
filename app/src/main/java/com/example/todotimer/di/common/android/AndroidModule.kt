package com.example.todotimer.di.common.android

import android.content.Context
import com.example.domain.common.core.service.DefaultTimeFormatService
import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.common.core.utils.Mapper
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import com.example.todotimer.screens.common.mapper.TodoMapper
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideTimeFormatService(): TimeFormatService = DefaultTimeFormatService()

    @Provides
    fun provideMapper(): Mapper<TodoData, TodoUiEntity> = TodoMapper()
}
