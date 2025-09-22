package org.demo.spring.practice.mapper

import org.demo.spring.practice.controller.dto.RequestCreateReservationDto
import org.demo.spring.practice.controller.dto.ResponseReservationDto
import org.demo.spring.practice.model.Reservation
import org.springframework.stereotype.Component

@Component
class ReservationMapper {
    fun toDomain(reservation: Reservation): ResponseReservationDto =
        ResponseReservationDto(
            id = reservation.id,
            userId = reservation.userId,
            roomId = reservation.roomId,
            startDate = reservation.startDate,
            endDate = reservation.endDate,
            status = reservation.status,
        )

    fun toEntity(reservation: RequestCreateReservationDto): Reservation =
        Reservation(
            userId = reservation.userId,
            roomId = reservation.roomId,
            endDate = reservation.endDate,
        )

    fun toEntity(reservation: ResponseReservationDto): Reservation =
        Reservation(
            id = reservation.id,
            userId = reservation.userId,
            roomId = reservation.roomId,
            startDate = reservation.startDate,
            endDate = reservation.endDate,
            status = reservation.status,
        )
}
