package com.example.domain.common.core.utils

interface DbConverter<ENTITY, DBDATA> {
    fun toDbEntity(from: ENTITY): DBDATA
    fun toEntity(from: DBDATA): ENTITY
}
