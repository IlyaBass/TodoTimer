package com.example.data.repo.todo

import com.example.domain.repo.todo.TodoRepo
import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Observable

class DefaultTodoRepo(
    private val todoStorage: TodoStorage
) : TodoRepo {

    override fun observe(): Observable<List<TodoData>> = todoStorage.observe()

    override fun observeById(todoId: Long): Observable<TodoData> = todoStorage.observeById(todoId)

    override fun add(data: TodoData) = todoStorage.add(data)

    override fun delete(id: Long) = todoStorage.delete(id)
}
