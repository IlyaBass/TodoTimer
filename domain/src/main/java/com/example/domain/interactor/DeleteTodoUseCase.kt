package com.example.domain.interactor

import com.example.domain.repo.todo.TodoRepo
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DeleteTodoUseCase(
    private val repo: TodoRepo,
    private val uiScheduler: Scheduler
) {

    fun execute(id: Long): Single<Unit> {
        return Single.fromCallable {
            repo.delete(id)
        }
            // to avoid bugs
            .delaySubscription(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(uiScheduler)
    }
}
