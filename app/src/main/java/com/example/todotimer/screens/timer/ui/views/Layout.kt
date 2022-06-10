package com.example.todotimer.screens.timer.ui.views

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotimer.screens.timer.viewmodel.TimerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.screens.common.theme.ItemBackground
import com.example.todotimer.service.TimerService
import com.example.todotimer.utils.isServiceRunning

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Layout(
	context: Context,
	todoId: Long,
	doBackPress: () -> Unit,
	viewModel: TimerViewModel = viewModel()
) {
	val todoTime = viewModel.todoTime.collectAsState().value

	if (todoTime.contentEquals("00:00:00") ||
		context.isServiceRunning(TimerService::class.java)
	) {
		viewModel.switchStartButton(false)
	} else {
		viewModel.switchStartButton(true)
	}

	BackButton(doBackPress)

	Column(
		modifier = Modifier.fillMaxSize()
	) {

		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
		) {
			Text(
				text = todoTime,
				fontSize = 40.sp,
				textAlign = TextAlign.Center,
			)
		}

		Card(
			backgroundColor = ItemBackground,
			modifier = Modifier
                .weight(1.3F)
                .fillMaxWidth(),
			elevation = 8.dp,
			shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
		) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.SpaceAround,
			) {
				TodoTitle()
				Buttons(todoId = todoId, context = context)
			}
		}
	}
}
