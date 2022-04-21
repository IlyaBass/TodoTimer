package com.example.domain.interactor

import com.example.domain.repo.todo.TodoRepo
import com.example.domain.repo.todo.entity.TodoData
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.time.LocalTime

class AddTodoUseCase(
    private val repo: TodoRepo,
    private val uiScheduler: Scheduler
) {

    fun execute(title: String, time: LocalTime): Single<Unit> {
        return Single.fromCallable {
            repo.add(TodoData(
                id = 0,
                title = title,
                time = time
            ))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(uiScheduler)
    }
}
