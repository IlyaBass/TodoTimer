package com.example.domain.common.core.utils

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}
