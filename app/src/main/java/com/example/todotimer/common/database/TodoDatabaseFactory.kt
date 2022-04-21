package com.example.todotimer.common.database

import android.content.Context
import androidx.room.Room
import com.example.domain.common.core.utils.Factory

private const val TODO_DATABASE_NAME = "todo"

class TodoDatabaseFactory(
    private val context: Context
) : Factory<TodoDatabase> {

    override fun getInstance(): TodoDatabase = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        TODO_DATABASE_NAME
    ).build()
}
