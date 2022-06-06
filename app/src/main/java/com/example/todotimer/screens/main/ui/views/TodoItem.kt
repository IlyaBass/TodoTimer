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
import com.example.todotimer.screens.common.entity.TodoUiEntity
import com.example.todotimer.screens.main.viewmodel.MainViewModel
import com.example.todotimer.screens.timer.ui.TimerActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.service.TimerService

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun TodoItem(
    context: Context,
    todoItem: TodoUiEntity,
    viewModel: MainViewModel = viewModel()
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 5.dp, 0.dp, 5.dp)
            .requiredHeight(60.dp)
            .combinedClickable(
                onClick = {
                    val intent = Intent(context, TimerActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                        .putExtra("todoId", todoItem.id)
                    startActivity(context, intent, null)
                },
                onLongClick = {
                    if (todoItem.id == TimerService.serviceId) {
                        context.stopService(Intent(context, TimerService::class.java))
                    }
                    viewModel.deleteItemById(todoItem.id)
                }),
        backgroundColor = Color(0xFFB0B6BF),
    ) {
        Text(
            modifier = Modifier.padding(7.dp),
            text = todoItem.title,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            color = Color(0xFF484E57)
        )
    }
}
