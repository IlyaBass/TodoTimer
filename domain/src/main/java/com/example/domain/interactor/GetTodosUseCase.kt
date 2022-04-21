package com.example.domain.interactor

import com.example.domain.repo.todo.TodoRepo
import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class GetTodosUseCase(
    private val repo: TodoRepo,
    private val uiScheduler: Scheduler
) {

    fun execute(): Observable<List<TodoData>> {
        return repo.observe()
            .subscribeOn(Schedulers.io())
            .observeOn(uiScheduler)
    }
}
