package org.demo.spring.practice.service

import org.demo.spring.practice.controller.dto.RequestCreateReservationDto
import org.demo.spring.practice.controller.dto.ResponseReservationDto
import org.demo.spring.practice.model.Reservation
import org.demo.spring.practice.model.ReservationStatus
import org.demo.spring.practice.repository.ReservationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReservationService(
    private val repo: ReservationRepository,
) {
    @Transactional
    fun createReservation(reservation: RequestCreateReservationDto) {
        val newReservation =
            Reservation(
                roomId = reservation.roomId,
                userId = reservation.userId,
                endDate = reservation.endDate,
            )

        repo.save(newReservation)
    }

    private fun checkIsAvailableReservation(id: Long?) {
        val reservation = findReservationById(id)

        if (reservation.status == ReservationStatus.APPROVED || reservation.status == ReservationStatus.CANCELED) {
            throw RuntimeException("Бронирование под номером $id занято. Пожалуйста выберите другое!")
        }
    }

    fun findReservationById(id: Long?): ResponseReservationDto {
        val reservation =
            repo.findReservationById(id)
                ?: throw RuntimeException("Бронирование не найдено!")

        return ResponseReservationDto(
            id = reservation?.id,
            userId = reservation?.userId,
            roomId = reservation?.roomId,
            startDate = reservation?.startDate,
            endDate = reservation?.endDate,
            status = reservation?.status,
        )
    }

    fun findAll(): List<ResponseReservationDto> =
        repo
            .findAll()
            .map {
                ResponseReservationDto(
                    id = it.id,
                    userId = it.userId,
                    roomId = it.roomId,
                    startDate = it.startDate,
                    endDate = it.endDate,
                    status = it.status,
                )
            }.toList()

    @Transactional
    fun cancelReservation(id: Long?): ResponseReservationDto? {
        val reservation = findReservationById(id)
        repo.updateStatusReservation(
            reservation.id,
            ReservationStatus.CANCELED,
        )
        return reservation
    }
}
