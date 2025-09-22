package org.demo.spring.practice.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "reservations")
data class Reservation(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @field:Column(name = "room_id", nullable = false)
    val roomId: Int? = null,
    @field:Column(name = "user_id", nullable = false)
    val userId: Int? = null,
    @field:Column(name = "start_date")
    val startDate: LocalDate? = LocalDate.now(),
    @field:Column(name = "end_date", nullable = false)
    val endDate: LocalDate? = null,
    @field:Enumerated(EnumType.STRING)
    var status: ReservationStatus? = ReservationStatus.PENDING,
)
