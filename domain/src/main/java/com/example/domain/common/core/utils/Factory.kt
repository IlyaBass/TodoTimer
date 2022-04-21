package com.example.domain.common.core.utils

interface Factory<T> {
    fun getInstance(): T
}
