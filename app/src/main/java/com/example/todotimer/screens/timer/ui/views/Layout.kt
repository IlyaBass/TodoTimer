package com.example.todotimer.screens.timer.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Layout() {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Need to wash the dishes",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "00:00:00",
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Remove")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Start")
            }
        }
    }
}
