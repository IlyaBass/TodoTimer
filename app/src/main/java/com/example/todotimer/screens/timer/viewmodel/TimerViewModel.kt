package com.example.todotimer.screens.timer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.common.core.utils.Mapper
import com.example.domain.interactor.ObserveTodoByIdUseCase
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import com.example.todotimer.utils.CountDownTimer
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerViewModel(
    private val observeTodoByIdUseCase: ObserveTodoByIdUseCase,
    private val mapper: Mapper<TodoData, TodoUiEntity>,
) : ViewModel() {

    private val _todoId = MutableStateFlow(0L)

    private val compositeDisposable = CompositeDisposable()

    lateinit var timer: CountDownTimer

    private val _todoTitle = MutableStateFlow("")
    val todoTitle = _todoTitle.asStateFlow()

    private val _todoTime = MutableStateFlow("00:00:10")
    val todoTime = _todoTime.asStateFlow()

    private val _todoRunning = MutableStateFlow(false)
    val todoRunning = _todoRunning.asStateFlow()

    private val _isStartButtonEnabled = MutableStateFlow(true)
    val isStartButtonEnabled = _isStartButtonEnabled.asStateFlow()

    fun observeTodoById(todoId: Long) {
        val disposable = observeTodoByIdUseCase.execute(todoId)
            .subscribe {
                val todoItem = mapper.map(it)
                _todoId.value = todoItem.id
                _todoTitle.value = todoItem.title
                _todoTime.value = todoItem.time
                _todoRunning.value = todoItem.running
            }
        compositeDisposable.add(disposable)
    }

    fun switchStartButton(value: Boolean) {
        _isStartButtonEnabled.value = value
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
