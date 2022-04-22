package com.example.domain.interactor

import com.example.domain.repo.todo.TodoRepo
import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class GetTodoByIdUseCase(
    private val repo: TodoRepo,
    private val uiScheduler: Scheduler
) {

    fun execute(todoId: Long): Observable<TodoData> {
        return repo.observeById(todoId)
            .subscribeOn(Schedulers.io())
            .observeOn(uiScheduler)
    }
}
