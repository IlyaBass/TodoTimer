package com.example.todotimer.repo.todo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity.Companion.TODO_NAME

@Entity(tableName = TODO_NAME)
class TodoDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = TITLE)
    val title: String,

    @ColumnInfo(name = TIME)
    val time: String,

    @ColumnInfo(name = RUNNING)
    val running: Boolean
) {
    companion object {
        const val TODO_NAME = "todo"
        const val ID = "id"
        const val TITLE = "title"
        const val TIME = "time"
        const val RUNNING = "running"
    }
}
