package com.example.todotimer.utils.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

inline fun <reified VM : ViewModel> viewModelFactory(
    crossinline buildViewModel: () -> VM
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VM::class.java)) {
            return buildViewModel() as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }
}
