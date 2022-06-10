package com.example.todotimer.screens.timer.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotimer.screens.common.theme.ItemText
import com.example.todotimer.screens.timer.viewmodel.TimerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoTitle(viewModel: TimerViewModel = viewModel()) {
	val todoTitle = viewModel.todoTitle.collectAsState().value

	Box(
		contentAlignment = Alignment.Center,
		modifier = Modifier
			.fillMaxWidth()
			.padding(23.dp, 0.dp),
	) {
		Text(
			text = todoTitle,
			fontSize = 24.sp,
			color = ItemText,
			textAlign = TextAlign.Center
		)
	}
}
