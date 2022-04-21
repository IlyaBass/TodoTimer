package com.example.todotimer.screens.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.todotimer.App
import com.example.todotimer.screens.common.theme.TodoTimerTheme
import com.example.todotimer.screens.main.ui.views.Dialog
import com.example.todotimer.screens.main.ui.views.Layout
import com.example.todotimer.screens.main.viewmodel.MainViewModel
import com.example.todotimer.screens.main.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.loadTodos()

        setContent {
            TodoTimerTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(
                                modifier = Modifier.alpha(viewModel.isFloatingBtnVisible.collectAsState().value),
                                onClick = { viewModel.showDialog() }
                            ) {
                                Icon(Icons.Filled.Add, "", tint = Color.White)
                            }
                        }
                    ) {
                        Layout(context = this)
                        Dialog()
                    }
                }
            }
        }
    }
}
