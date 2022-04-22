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
import androidx.core.content.ContextCompat.startActivity
import com.example.todotimer.screens.timer.viewmodel.TimerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.screens.main.ui.MainActivity

@Composable
fun Layout(
    context: Context,
    viewModel: TimerViewModel = viewModel()
) {
    val todoTitle = viewModel.todoTitle.collectAsState().value
    val todoTime = viewModel.todoTime.collectAsState().value
    val timerControlBtnTitle = viewModel.timerControlBtnTitle.collectAsState().value
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
            Button(onClick = {
                viewModel.removeTodoById()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(context, intent, null)
            }) {
                Text(text = "Remove")
            }
            Button(onClick = { viewModel.controlTimer() }) {
                Text(text = timerControlBtnTitle)
            }
        }
    }
}
