package com.example.domain.interactor

import com.example.domain.repo.todo.TodoRepo
import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AddTodoUseCase(
    private val repo: TodoRepo,
    private val uiScheduler: Scheduler
) {

    fun execute(title: String, time: String, running: Boolean): Single<Unit> {
        return Single.fromCallable {
            repo.add(TodoData(
                id = 0,
                title = title,
                time = time,
                running = running
            ))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(uiScheduler)
    }
}
