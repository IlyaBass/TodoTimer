package com.example.todotimer

import android.app.Application
import com.example.todotimer.di.appcomponent.AppComponent
import com.example.todotimer.di.appcomponent.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
