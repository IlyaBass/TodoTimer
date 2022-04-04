package com.example.todotimer.screens.main.mapper

import com.example.domain.common.core.utils.Mapper
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.main.entity.TodoUiEntity

class TodoMapper : Mapper<TodoData, TodoUiEntity> {

    override fun map(from: TodoData): TodoUiEntity = from.run {
        TodoUiEntity(
            id = id,
            title = title
        )
    }
}