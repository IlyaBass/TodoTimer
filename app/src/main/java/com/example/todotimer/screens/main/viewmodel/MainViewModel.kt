package com.example.todotimer.screens.main.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.common.core.utils.Mapper
import com.example.domain.interactor.AddTodoUseCase
import com.example.domain.interactor.DeleteTodoUseCase
import com.example.domain.interactor.GetTodosUseCase
import com.example.domain.repo.todo.entity.TodoData
import com.example.todotimer.screens.common.entity.TodoUiEntity
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@SuppressLint("CheckResult")
class MainViewModel(
    private val mapper: Mapper<TodoData, TodoUiEntity>,
    private val getTodosUseCase: GetTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val timeFormatService: TimeFormatService
) : ViewModel() {

    private val _todoList = MutableStateFlow(listOf<TodoUiEntity>())
    val todoList = _todoList.asStateFlow()

    private val _isFloatingBtnVisible = MutableStateFlow(1F)
    val isFloatingBtnVisible = _isFloatingBtnVisible.asStateFlow()

    private val _dialogTodoTitle = MutableStateFlow("")
    val dialogTodoTitle = _dialogTodoTitle.asStateFlow()

    private val _isDialogVisible = MutableStateFlow(false)
    val isDialogVisible = _isDialogVisible.asStateFlow()

    private val _hoursValue = MutableStateFlow(0)
    private val _minutesValue = MutableStateFlow(0)
    private val _secondsValue = MutableStateFlow(0)

    private fun formatStringToTime(): Long {
        if (_hoursValue.value == 0 && _minutesValue.value == 0 && _secondsValue.value == 0) {
            _secondsValue.value = 10
        }
        val hours = if (_hoursValue.value.toString().length < 2) {
            "0${_hoursValue.value}"
        } else {
            _hoursValue.value.toString()
        }
        val minutes = if (_minutesValue.value.toString().length < 2) {
            "0${_minutesValue.value}"
        } else {
            _minutesValue.value.toString()
        }
        val seconds = if (_secondsValue.value.toString().length < 2) {
            "0${_secondsValue.value}"
        } else {
            _secondsValue.value.toString()
        }
        val time = "${hours}:${minutes}:${seconds}"
        return timeFormatService.fromPattern(time)
    }

    private fun resetAllValues() {
        _isDialogVisible.value = false
        _dialogTodoTitle.value = ""
        _hoursValue.value = 0
        _minutesValue.value = 0
        _secondsValue.value = 0
    }

    fun loadTodos(): Disposable {
        return getTodosUseCase.execute()
            .subscribe { todoDataList ->
                _todoList.value = todoDataList.map { mapper.map(it) }
            }
    }

    fun onListScrolled(firstVisibleItemOffset: Int) {
        if (firstVisibleItemOffset > 0) {
            _isFloatingBtnVisible.value = 0F
        } else {
            _isFloatingBtnVisible.value = 1F
        }
    }

    @MainThread
    fun deleteItemById(id: Long) {
        deleteTodoUseCase.execute(id).subscribe()
    }

    fun saveTodo() {
        addTodoUseCase.execute(
            title = _dialogTodoTitle.value,
            time = formatStringToTime(),
            running = false
        ).subscribe()
        resetAllValues()
    }

    fun changeHoursValue(value: Int) {
        _hoursValue.value = value
    }

    fun changeMinutesValue(value: Int) {
        _minutesValue.value = value
    }

    fun changeSecondsValue(value: Int) {
        _secondsValue.value = value
    }

    fun changeTodoTitle(text: String) {
        _dialogTodoTitle.value = text
    }

    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun hideDialog() {
        resetAllValues()
    }
}
