package com.example.todotimer.screens.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.todotimer.App
import com.example.todotimer.screens.common.theme.Green
import com.example.todotimer.screens.common.theme.ScreenBackground
import com.example.todotimer.screens.common.theme.TodoTimerTheme
import com.example.todotimer.screens.main.ui.views.TodoDialog
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
                Scaffold(
                    floatingActionButton = {
                        if (viewModel.isFloatingBtnVisible.collectAsState().value) {
                            FloatingActionButton(
                                backgroundColor = Green,
                                onClick = { viewModel.showDialog() }
                            ) {
                                Icon(Icons.Filled.Add, "", tint = Color.White)
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    backgroundColor = ScreenBackground,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Layout(context = this)
                    TodoDialog()
                }
            }
        }
    }
}
