package com.example.todotimer.screens.common.entity

data class TodoUiEntity(
    val id: Long,
    val title: String,
    var time: String,
    var running: Boolean
)
