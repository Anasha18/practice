package org.demo.spring.practice.controller.dto

import org.demo.spring.practice.model.ReservationStatus
import java.time.LocalDate

data class ResponseReservationDto(
    val id: Long? = null,
    val userId: Int? = null,
    val roomId: Int? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    var status: ReservationStatus? = null,
)
