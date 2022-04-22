package com.example.domain.interactor

import com.example.domain.repo.todo.TodoRepo
import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UpdateTodoUseCase(
    private val repo: TodoRepo,
    private val uiScheduler: Scheduler
) {

    fun execute(id: Long, title: String, time: Long): Single<Unit> = Single.fromCallable {
        repo.update(
            TodoData(
                id = id,
                title = title,
                time = time
            )
        )
    }
        .subscribeOn(Schedulers.io())
        .observeOn(uiScheduler)
}
