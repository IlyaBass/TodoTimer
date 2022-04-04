package com.example.todotimer.di.appcomponent

import com.example.todotimer.di.interactor.InteractorModule
import com.example.todotimer.di.repo.RepoModule
import com.example.todotimer.di.screens.main.MainModule
import com.example.todotimer.di.screens.timer.TimerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        InteractorModule::class,
        RepoModule::class,
        MainModule::class,
        TimerModule::class
    ]
)
interface AppComponent {}
