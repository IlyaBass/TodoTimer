package com.example.todotimer.common.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.domain.common.core.utils.Factory

private const val TODO_DATABASE_NAME = "todo"

class TodoDatabaseFactory(
    private val context: Context
) : Factory<TodoDatabase> {

    override fun getInstance(): TodoDatabase = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        TODO_DATABASE_NAME
    )
        .addMigrations(MIGRATION_1_2)
        .build()
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN running INTEGER DEFAULT 0"
        )
    }
}
