package com.example.todotimer.common.database

import android.content.Context
import androidx.room.Room

private const val TODO_DATABASE_NAME = "todo"

class TodoDatabaseFactory(
    private val context: Context
) {

    fun getInstance(): TodoDatabase = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        TODO_DATABASE_NAME
    ).build()
}
