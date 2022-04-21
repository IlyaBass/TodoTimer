package com.example.domain.interactor

import com.example.domain.repo.todo.TodoRepo
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DeleteTodoUseCase(
    private val repo: TodoRepo,
    private val uiScheduler: Scheduler
) {

    fun execute(id: Long): Single<Unit> {
        return Single.fromCallable {
            repo.delete(id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(uiScheduler)
    }
}
