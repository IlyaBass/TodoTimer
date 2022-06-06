package com.example.todotimer.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todotimer.repo.todo.database.TodoDao
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity

@Database(
    entities = [TodoDatabaseEntity::class],
    version = 2,
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
