package com.example.todotimer.repo.todo

import com.example.data.repo.todo.TodoStorage
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.repo.todo.database.TodoDao
import com.example.todotimer.repo.todo.database.converter.TodoConverter
import io.reactivex.Observable


class TodoDatabaseStorage(
    private val todoDao: TodoDao,
    private val todoConverter: TodoConverter
) : TodoStorage {

    override fun observe(): Observable<List<TodoData>> = todoDao.observe()
        .toObservable()
        .map { it.map(todoConverter::toEntity) }

    override fun add(data: TodoData) = todoDao.add(todoConverter.toDbEntity(data))

    override fun delete(id: Long) = todoDao.delete(id)
}
