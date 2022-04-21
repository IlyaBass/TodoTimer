package com.example.todotimer.di.common.android

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}
