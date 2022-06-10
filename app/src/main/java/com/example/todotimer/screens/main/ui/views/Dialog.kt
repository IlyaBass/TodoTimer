package com.example.todotimer.screens.main.ui.views

import android.widget.NumberPicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotimer.R
import com.example.todotimer.screens.common.theme.ItemBackground
import com.example.todotimer.screens.common.theme.ItemText
import com.example.todotimer.screens.common.theme.Red
import com.example.todotimer.screens.main.viewmodel.MainViewModel

@Composable
fun TodoDialog(viewModel: MainViewModel = viewModel()) {
	val dialogTodoTitle = viewModel.dialogTodoTitle.collectAsState().value
	val isDialogVisible = viewModel.isDialogVisible.collectAsState().value
	if (isDialogVisible) {
		Dialog(onDismissRequest = { viewModel.hideDialog() }) {
			Surface(
				shape = RoundedCornerShape(20.dp),
				color = ItemBackground
			) {
				Column(
					modifier = Modifier.padding(25.dp)
				) {
					DialogTextField(dialogTodoTitle = dialogTodoTitle)
					NumberPicker()
					DialogButtons(dialogTodoTitle = dialogTodoTitle)
				}
			}
		}
	}
}

@Composable
fun DialogTextField(
	dialogTodoTitle: String,
	viewModel: MainViewModel = viewModel(),
) {
	TextField(
		singleLine = true,
		colors = TextFieldDefaults.textFieldColors(
			textColor = ItemText,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
			disabledIndicatorColor = Color.Transparent
		),
		shape = RoundedCornerShape(20.dp),
		value = dialogTodoTitle,
		onValueChange = { viewModel.changeTodoTitle(it) },
		placeholder = {
			Text(
				text = "Enter todo",
				fontSize = 20.sp
			)
		},
		textStyle = TextStyle(
			fontSize = 20.sp,
			color = ItemText,
			textDecoration = TextDecoration.None,
			fontFamily = FontFamily(
				Font(R.font.overpass_bold, FontWeight.Normal)
			)
		),
	)
}

@Composable
fun DialogButtons(
	dialogTodoTitle: String,
	viewModel: MainViewModel = viewModel()
) {
	Row(
		modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
		horizontalArrangement = Arrangement.SpaceAround
	) {
		Button(
			shape = RoundedCornerShape(25.dp),
			contentPadding = PaddingValues(0.dp, 10.dp),
			modifier = Modifier.width(100.dp),
			elevation = ButtonDefaults.elevation(
				defaultElevation = 4.dp,
				pressedElevation = 0.dp,
				disabledElevation = 0.dp
			),
			onClick = { viewModel.hideDialog() },
			colors = ButtonDefaults.buttonColors(
				contentColor = Color.White,
				backgroundColor = Red
			)
		) {
			Text(text = "Cancel", fontSize = 20.sp)
		}
		Button(
			shape = RoundedCornerShape(25.dp),
			contentPadding = PaddingValues(0.dp, 10.dp),
			modifier = Modifier.width(100.dp),
			elevation = ButtonDefaults.elevation(
				defaultElevation = 4.dp,
				pressedElevation = 0.dp,
				disabledElevation = 0.dp
			),
			enabled = dialogTodoTitle.isNotBlank(),
			onClick = { viewModel.saveTodo() },
		) {
			Text(text = "Save", fontSize = 20.sp)
		}
	}
}

@Composable
fun NumberPicker(viewModel: MainViewModel = viewModel()) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceAround
	) {
		AndroidView(
			{
				NumberPicker(it).apply {
					setOnValueChangedListener { _, _, newValue ->
						viewModel.changeHoursValue(newValue)
					}
					minValue = 0
					maxValue = 12
				}
			}
		)
		AndroidView(
			{
				NumberPicker(it).apply {
					setOnValueChangedListener { _, _, newValue ->
						viewModel.changeMinutesValue(newValue)
					}
					minValue = 0
					maxValue = 59
				}
			}
		)
		AndroidView(
			{
				NumberPicker(it).apply {
					setOnValueChangedListener { _, _, newValue ->
						viewModel.changeSecondsValue(newValue)
					}
					minValue = 0
					maxValue = 59
				}
			}
		)
	}
}
