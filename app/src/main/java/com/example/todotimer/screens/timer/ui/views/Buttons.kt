package com.example.todotimer.screens.timer.ui.views

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.todotimer.screens.common.theme.Red
import com.example.todotimer.service.TimerService
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.screens.timer.viewmodel.TimerViewModel
import com.example.todotimer.utils.isServiceRunning

@Composable
fun Buttons(
	viewModel: TimerViewModel = viewModel(),
	context: Context,
	todoId: Long,
) {
	val todoRunning = viewModel.todoRunning.collectAsState().value
	val isStartButtonEnabled = viewModel.isStartButtonEnabled.collectAsState().value

	val isServicePaused = TimerService.isServicePaused

	Column(
		verticalArrangement = Arrangement.Center,
		modifier = Modifier.padding(bottom = 45.dp)
	) {
		Button(
			enabled = isStartButtonEnabled,
			shape = RoundedCornerShape(25.dp),
			modifier = Modifier
				.padding(bottom = 10.dp)
				.width(193.dp),
			contentPadding = PaddingValues(0.dp, 20.dp),
			elevation = ButtonDefaults.elevation(
				defaultElevation = 4.dp,
				pressedElevation = 0.dp,
				disabledElevation = 0.dp
			),
			onClick = {
				ContextCompat.startForegroundService(
					context, Intent(context, TimerService::class.java)
						.putExtra("todoId", todoId)
				)
				isServicePaused.value = false
			}) {
			Text(text = "Start", fontSize = 24.sp)
		}

		Button(
			enabled = todoRunning,
			shape = RoundedCornerShape(25.dp),
			modifier = Modifier
				.padding(top = 10.dp)
				.width(193.dp),
			contentPadding = PaddingValues(0.dp, 20.dp),
			elevation = ButtonDefaults.elevation(
				defaultElevation = 4.dp,
				pressedElevation = 0.dp,
				disabledElevation = 0.dp
			),
			colors = ButtonDefaults.buttonColors(
				contentColor = Color.White,
				backgroundColor = Red
			),
			onClick = {
				isServicePaused.value = true
				context.stopService(Intent(context, TimerService::class.java))
				viewModel.switchStartButton(!context.isServiceRunning(TimerService::class.java))
			}) {
			Text(text = "Stop", fontSize = 24.sp)
		}
	}
}
