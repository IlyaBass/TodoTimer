package com.example.todotimer.screens.main.ui.views

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.todotimer.screens.common.entity.TodoUiEntity
import com.example.todotimer.screens.main.viewmodel.MainViewModel
import com.example.todotimer.screens.timer.ui.TimerActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.R
import com.example.todotimer.screens.common.theme.ItemBackground
import com.example.todotimer.screens.common.theme.ItemText
import com.example.todotimer.screens.common.theme.Red
import com.example.todotimer.service.TimerService

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun TodoItem(
    context: Context,
    todoItem: TodoUiEntity,
    viewModel: MainViewModel = viewModel()
) {

    Surface(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(90.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    val intent = Intent(context, TimerActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                        .putExtra("todoId", todoItem.id)
                    startActivity(context, intent, null)
                },
            backgroundColor = ItemBackground,
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.85F)
                            .padding(19.dp, 0.dp, 0.dp, 0.dp),
                        text = todoItem.title,
                        fontSize = 24.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = ItemText
                    )

                    IconButton(
                        modifier = Modifier.padding(0.dp, 0.dp, 7.dp, 0.dp),
                        onClick = {
                        if (todoItem.id == TimerService.serviceId.value) {
                            context.stopService(Intent(context, TimerService::class.java))
                        }
                        viewModel.deleteItemById(todoItem.id)
                    }) {
                        Icon(
                            modifier = Modifier.size(27.dp),
                            painter = painterResource(id = R.drawable.ic_trash),
                            contentDescription = "",
                            tint = Red
                        )
                    }
                }
            }
        }
    }
}
