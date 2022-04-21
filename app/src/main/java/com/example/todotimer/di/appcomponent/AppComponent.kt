package com.example.todotimer.di.appcomponent

import com.example.todotimer.di.common.android.AndroidModule
import com.example.todotimer.di.common.database.DatabaseModule
import com.example.todotimer.di.interactor.InteractorModule
import com.example.todotimer.di.repo.TodoRepoModule
import com.example.todotimer.di.screens.main.MainModule
import com.example.todotimer.di.screens.timer.TimerModule
import com.example.todotimer.screens.main.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidModule::class,
        InteractorModule::class,
        TodoRepoModule::class,
        MainModule::class,
        TimerModule::class,
        DatabaseModule::class,
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
