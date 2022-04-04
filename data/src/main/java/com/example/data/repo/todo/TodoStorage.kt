package com.example.data.repo.todo

import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Observable

interface TodoStorage {

    fun observe(): Observable<List<TodoData>>

    fun add(data: TodoData)

    fun delete(id: Long)
}
