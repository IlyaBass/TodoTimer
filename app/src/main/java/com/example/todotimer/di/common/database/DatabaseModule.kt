package com.example.todotimer.di.common.database

import android.content.Context
import com.example.todotimer.common.database.TodoDatabase
import com.example.todotimer.common.database.TodoDatabaseFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    internal fun provideTodoDatabase(
        context: Context
    ): TodoDatabase = TodoDatabaseFactory(context).getInstance()
}
