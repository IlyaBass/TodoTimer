package com.example.todotimer.screens.timer.ui.views

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startForegroundService
import com.example.todotimer.screens.timer.viewmodel.TimerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.service.TimerService
import com.example.todotimer.utils.isServiceRunning

@Composable
fun Layout(
    context: Context,
    todoId: Long,
    viewModel: TimerViewModel = viewModel()
) {
    val todoTitle = viewModel.todoTitle.collectAsState().value
    val todoTime = viewModel.todoTime.collectAsState().value
    val todoRunning = viewModel.todoRunning.collectAsState().value
    val isStartButtonEnabled = viewModel.isStartButtonEnabled.collectAsState().value

    if (todoTime.contentEquals("00:00:00") || context.isServiceRunning(TimerService::class.java)) {
        viewModel.switchStartButton(false)
    } else {
        viewModel.switchStartButton(true)
    }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todoTitle,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = todoTime,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                enabled = isStartButtonEnabled,
                onClick = {
                    startForegroundService(
                        context, Intent(context, TimerService::class.java)
                            .putExtra("todoId", todoId)
                    )
                }) {
                Text(text = "Start")
            }

            Button(
                enabled = todoRunning,
                onClick = {
                    context.stopService(Intent(context, TimerService::class.java))
                    viewModel.switchStartButton(!context.isServiceRunning(TimerService::class.java))
                }) {
                Text(text = "Stop")
            }
        }
    }
}
