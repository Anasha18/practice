package org.demo.spring.practice.controller.dto

import java.time.LocalDate

data class RequestCreateReservationDto(
    val roomId: Int? = null,
    val userId: Int? = null,
    val endDate: LocalDate? = null,
)
