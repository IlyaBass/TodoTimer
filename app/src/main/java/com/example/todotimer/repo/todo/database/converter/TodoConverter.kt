package com.example.todotimer.repo.todo.database.converter

import com.example.domain.common.core.utils.DbConverter
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TodoConverter : DbConverter<TodoData, TodoDatabaseEntity> {

    override fun toDbEntity(from: TodoData): TodoDatabaseEntity {
        return with(from) {
            TodoDatabaseEntity(
                id = id,
                title = title,
                time = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            )
        }
    }

    override fun toEntity(from: TodoDatabaseEntity): TodoData {
        return with(from) {
            TodoData(
                id = id,
                title = title,
                time = LocalTime.parse(time)
            )
        }
    }
}
