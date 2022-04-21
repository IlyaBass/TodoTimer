package com.example.todotimer.di.common.database

import android.content.Context
import com.example.todotimer.common.database.TodoDatabase
import com.example.todotimer.common.database.TodoDatabaseFactory
import com.example.todotimer.di.common.android.AndroidModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(
    includes = [AndroidModule::class]
)
object DatabaseModule {

    @Singleton
    @Provides
    internal fun provideTodoDatabase(
        context: Context
    ): TodoDatabase = TodoDatabaseFactory(context).getInstance()
}
