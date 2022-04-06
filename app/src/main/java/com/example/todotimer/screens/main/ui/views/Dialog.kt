package com.example.todotimer.screens.main.ui.views

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.screens.main.viewmodel.MainViewModel

@Composable
fun Dialog(viewModel: MainViewModel = viewModel()) {
    val dialogTodoTitle = viewModel.dialogTodoTitle.collectAsState().value
    val isDialogVisible = viewModel.isDialogVisible.collectAsState().value
    if (isDialogVisible) {
        AlertDialog(
            modifier = Modifier.padding(10.dp),
            onDismissRequest = { /*TODO*/ },
            title = {
                TextField(
                    value = dialogTodoTitle,
                    onValueChange = { viewModel.changeTodoTitle(it) },
                    placeholder = { Text(text = "Enter todo") }
                )
            },
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    AndroidView(
                        {
                            NumberPicker(it).apply {
                                setOnValueChangedListener { _, _, _ -> }
                                minValue = 0
                                maxValue = 12
                            }
                        }
                    )
                    AndroidView(
                        {
                            NumberPicker(it).apply {
                                setOnValueChangedListener { _, _, _ -> }
                                minValue = 0
                                maxValue = 60
                            }
                        }
                    )
                    AndroidView(
                        {
                            NumberPicker(it).apply {
                                setOnValueChangedListener { _, _, _ -> }
                                minValue = 0
                                maxValue = 60
                            }
                        }
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = { viewModel.hideDialog() }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Save")
                    }
                }
            }
        )
    }
}
