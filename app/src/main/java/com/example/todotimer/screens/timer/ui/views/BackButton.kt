package com.example.todotimer.screens.timer.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todotimer.R

@Composable
fun BackButton(doBackPress: () -> Unit) {
	Box(
		modifier = Modifier.padding(16.dp)
	) {
		IconButton(onClick = { doBackPress() }) {
			Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "")
		}
	}
}
