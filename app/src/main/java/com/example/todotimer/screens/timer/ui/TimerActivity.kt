package com.example.todotimer.screens.timer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todotimer.screens.common.theme.TodoTimerTheme
import com.example.todotimer.screens.timer.ui.views.Layout

class TimerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTimerTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Layout()
                }
            }
        }
    }
}
