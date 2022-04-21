package com.example.domain.repo.todo.entity

import java.time.LocalTime

data class TodoData(
    val id: Long,
    val title: String,
    val time: LocalTime
)
