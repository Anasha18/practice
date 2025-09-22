package org.demo.spring.practice.exception.dto

import java.time.LocalDateTime

data class ErrorResponseDto(
    val message: String,
    val detailMessage: String,
    val errorTime: LocalDateTime,
)
