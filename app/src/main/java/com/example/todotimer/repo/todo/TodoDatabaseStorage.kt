package com.example.todotimer.repo.todo

import com.example.data.repo.todo.TodoStorage
import com.example.domain.common.core.utils.DbConverter
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.repo.todo.database.TodoDao
import com.example.todotimer.repo.todo.database.entity.TodoDatabaseEntity
import io.reactivex.Observable

class TodoDatabaseStorage(
    private val todoDao: TodoDao,
    private val todoConverter: DbConverter<TodoData, TodoDatabaseEntity>
) : TodoStorage {

    override fun observe(): Observable<List<TodoData>> = todoDao.observe()
        .toObservable()
        .map { it.map(todoConverter::toEntity) }

    override fun observeById(todoId: Long): Observable<TodoData> = todoDao.observeById(todoId)
        .toObservable()
        .map (todoConverter::toEntity)

    override fun add(data: TodoData) = todoDao.add(todoConverter.toDbEntity(data))

    override fun delete(id: Long) = todoDao.delete(id)
}
