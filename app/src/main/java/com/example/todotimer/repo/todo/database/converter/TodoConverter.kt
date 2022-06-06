package com.example.todotimer.repo.todo.database.converter

import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.common.core.utils.DbConverter
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity

class TodoConverter(
    private val timeFormatService: TimeFormatService
) : DbConverter<TodoData, TodoDatabaseEntity> {

    override fun toDbEntity(from: TodoData): TodoDatabaseEntity {
        return with(from) {
            TodoDatabaseEntity(
                id = id,
                title = title,
                time = timeFormatService.toPattern(time),
                running = running
            )
        }
    }

    override fun toEntity(from: TodoDatabaseEntity): TodoData {
        return with(from) {
            TodoData(
                id = id,
                title = title,
                time = timeFormatService.fromPattern(time),
                running = running
            )
        }
    }
}
