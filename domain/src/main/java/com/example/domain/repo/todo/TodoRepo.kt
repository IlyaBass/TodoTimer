package com.example.domain.repo.todo

import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Observable

interface TodoRepo {

    fun observe(): Observable<List<TodoData>>

    fun observeById(todoId: Long): Observable<TodoData>

    fun add(data: TodoData)

    fun update(data: TodoData)

    fun delete(id: Long)
}
