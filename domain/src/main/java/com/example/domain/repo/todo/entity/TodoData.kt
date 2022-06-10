package com.example.domain.repo.todo.entity

data class TodoData(
    val id: Long,
    val title: String,
    val time: String,
    val running: Boolean
)
