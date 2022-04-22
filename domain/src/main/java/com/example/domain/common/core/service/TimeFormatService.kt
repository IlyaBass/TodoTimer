package com.example.domain.common.core.service


interface TimeFormatService {

    fun toPattern(time: Long): String

    fun fromPattern(string: String): Long
}
