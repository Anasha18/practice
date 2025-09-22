package org.demo.spring.practice.controller.dto

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class RequestCreateReservationDto(
    @field:NotNull
    val roomId: Int? = null,
    @field:NotNull
    val userId: Int? = null,
    @field:NotNull
    @field:FutureOrPresent
    val endDate: LocalDate? = null,
)
