package com.example.todotimer.screens.timer.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.common.core.utils.Mapper
import com.example.domain.interactor.DeleteTodoUseCase
import com.example.domain.interactor.GetTodoByIdUseCase
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import com.example.todotimer.utils.CountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val START = "Start"
private const val PAUSE = "Pause"

@SuppressLint("CheckResult")
class TimerViewModel(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val mapper: Mapper<TodoData, TodoUiEntity>,
    private val timeFormatService: TimeFormatService
) : ViewModel() {

    private val _todoId = MutableStateFlow(0L)
    private val _wasTimerPaused = MutableStateFlow(false)

    lateinit var timer: CountDownTimer

    private val _todoTitle = MutableStateFlow("")
    val todoTitle = _todoTitle.asStateFlow()

    private val _todoTime = MutableStateFlow("00:00:10")
    val todoTime = _todoTime.asStateFlow()

    private val _timerControlBtnTitle = MutableStateFlow(START)
    val timerControlBtnTitle = _timerControlBtnTitle.asStateFlow()

    // TODO: Make timer implementation

    fun getTodoById(todoId: Long) {
        getTodoByIdUseCase.execute(todoId)
            .subscribe {
                val todoItem = mapper.map(it)
                _todoId.value = todoItem.id
                _todoTitle.value = todoItem.title
                _todoTime.value = todoItem.time

                timer = object : CountDownTimer(
                    timeFormatService.fromPattern(todoTime.value),
                    1000
                ) {

                    override fun onTick(millisUntilFinished: Long) {
                        _todoTime.value = timeFormatService.toPattern(millisUntilFinished)
                    }

                    override fun onFinish() {
                        Log.d("myLogger", "finished")
                    }
                }
            }
    }

    fun removeTodoById(todoId: Long = _todoId.value) {
        deleteTodoUseCase.execute(todoId).subscribe()
    }

    fun controlTimer() {
        if (_timerControlBtnTitle.value == START) {
            _timerControlBtnTitle.value = PAUSE
            if (!_wasTimerPaused.value) {
                timer.start()
            } else {
                timer.resume()
            }
        } else {
            _timerControlBtnTitle.value = START
            _wasTimerPaused.value = true
            timer.pause()
        }
    }
}
