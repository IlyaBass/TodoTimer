package com.example.todotimer.screens.timer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.todotimer.App
import com.example.todotimer.screens.common.theme.ScreenBackground
import com.example.todotimer.screens.common.theme.TodoTimerTheme
import com.example.todotimer.screens.timer.ui.views.Layout
import com.example.todotimer.screens.timer.viewmodel.TimerViewModel
import com.example.todotimer.screens.timer.viewmodel.TimerViewModelFactory
import javax.inject.Inject

class TimerActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: TimerViewModelFactory

    private lateinit var viewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[TimerViewModel::class.java]

        val todoId = intent.getLongExtra("todoId", 0L)

        viewModel.observeTodoById(todoId)

        setContent {
            TodoTimerTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = ScreenBackground
                ) {
                    Layout(this, todoId, { onBackPressed() })
                }
            }
        }
    }
}
