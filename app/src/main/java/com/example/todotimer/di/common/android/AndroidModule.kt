package com.example.todotimer.di.common.android

import android.content.Context
import com.example.todotimer.App
import dagger.Module
import dagger.Provides

@Module
object AndroidModule {

    @Provides
    @JvmStatic
    internal fun provideApplicationContext(
        app: App
    ): Context = app
}
