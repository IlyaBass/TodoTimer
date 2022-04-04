package com.example.todotimer.screens.main.ui.views

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.todotimer.screens.timer.ui.TimerActivity

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun TodoItem(
    context: Context,
    todoItem: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 5.dp, 0.dp, 5.dp)
            .requiredHeight(60.dp)
            .combinedClickable(
                onClick = {
                    val intent = Intent(context, TimerActivity::class.java)
                    startActivity(context, intent, null)
                    // TODO(must go to activity to manage time and the item)
                },
                onLongClick = {
                    // TODO(must delete item)
                }
            ),
        backgroundColor = Color(0xFFB0B6BF),
    ) {
        Text(
            modifier = Modifier.padding(7.dp),
            text = todoItem,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            color = Color(0xFF484E57)
        )
    }
}
