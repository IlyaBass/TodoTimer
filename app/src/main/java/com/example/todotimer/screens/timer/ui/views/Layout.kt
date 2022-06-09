package com.example.todotimer.screens.timer.ui.views

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startForegroundService
import com.example.todotimer.screens.timer.viewmodel.TimerViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.R
import com.example.todotimer.screens.common.theme.ItemBackground
import com.example.todotimer.screens.common.theme.ItemText
import com.example.todotimer.screens.common.theme.Red
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
    val todoTitle = viewModel.todoTitle.collectAsState().value
    val todoTime = viewModel.todoTime.collectAsState().value
    val todoRunning = viewModel.todoRunning.collectAsState().value
    val isStartButtonEnabled = viewModel.isStartButtonEnabled.collectAsState().value

    if (todoTime.contentEquals("00:00:00") || context.isServiceRunning(TimerService::class.java)) {
        viewModel.switchStartButton(false)
    } else {
        viewModel.switchStartButton(true)
    }

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        IconButton(onClick = { doBackPress() }) {
            Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "")
        }
    }

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
                            startForegroundService(
                                context, Intent(context, TimerService::class.java)
                                    .putExtra("todoId", todoId)
                            )
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
                            context.stopService(Intent(context, TimerService::class.java))
                            viewModel.switchStartButton(!context.isServiceRunning(TimerService::class.java))
                        }) {
                        Text(text = "Stop", fontSize = 24.sp)
                    }
                }
            }
        }
    }
}
