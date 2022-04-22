package com.example.domain.common.core.service

import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.TimeUnit

const val HH_MM_SS_PATTERN: String = "HH:mm:ss"

class DefaultTimeFormatService : TimeFormatService {

    @Suppress("SimpleDateFormat")
    private val dateTimeFormatter = SimpleDateFormat(HH_MM_SS_PATTERN)

    override fun toPattern(time: Long): String = String.format(
        "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time),
        TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(time)
        ),
        TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(time)
        )
    )

    override fun fromPattern(string: String): Long {
        dateTimeFormatter.timeZone = TimeZone.getTimeZone(ZoneOffset.UTC)
        return dateTimeFormatter.parse(string).time
    }
}
