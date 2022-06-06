package com.example.todotimer.screens.common.mapper

import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.common.core.utils.Mapper
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity

class TodoMapper(
    private val timeFormatService: TimeFormatService
) : Mapper<TodoData, TodoUiEntity> {

    override fun map(from: TodoData): TodoUiEntity = from.run {
        TodoUiEntity(
            id = id,
            title = title,
            time = timeFormatService.toPattern(time),
            running = running
        )
    }
}
