package com.core.common.error

interface GlobalErrorMapper {
    fun map(throwable: Throwable): GlobalErrorType
}