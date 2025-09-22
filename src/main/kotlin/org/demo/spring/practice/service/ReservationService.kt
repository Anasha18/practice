package org.demo.spring.practice.service

import org.demo.spring.practice.controller.dto.RequestCreateReservationDto
import org.demo.spring.practice.controller.dto.ResponseReservationDto
import org.demo.spring.practice.mapper.ReservationMapper
import org.demo.spring.practice.model.ReservationStatus
import org.demo.spring.practice.repository.ReservationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ReservationService(
    private val repo: ReservationRepository,
    private val mapper: ReservationMapper,
) {
    @Transactional
    fun createReservation(reservation: RequestCreateReservationDto): ResponseReservationDto {
        if (!reservation.endDate?.isAfter(LocalDate.now())!!) {
            throw IllegalArgumentException("Бронирование не может быть в прошлом!")
        }

        val entityToSave = mapper.toEntity(reservation)

        val savedEntity = repo.save(entityToSave)
        return mapper.toDomain(savedEntity)
    }

    fun findReservationById(id: Long?): ResponseReservationDto {
        val reservation = repo.findReservationById(id) ?: throw RuntimeException("Бронирование не найдено!")

        return mapper.toDomain(reservation)
    }

    fun findAll(): List<ResponseReservationDto> =
        repo
            .findAll()
            .map {
                mapper.toDomain(it)
            }.toList()

    @Transactional
    fun cancelReservation(id: Long?): ResponseReservationDto? {
        val searchReservationToEntity = findReservationById(id)
        val reservationToUpdated = mapper.toEntity(searchReservationToEntity)

        if (reservationToUpdated.status == ReservationStatus.APPROVED) {
            throw IllegalArgumentException("Невозможно отменить подтверждённое бронирование.")
        }
        if (reservationToUpdated.status == ReservationStatus.CANCELED) {
            throw IllegalArgumentException("Невозможно отменить отмененное бронирование.")
        }

        reservationToUpdated.status = ReservationStatus.CANCELED
        repo.save(reservationToUpdated)
        return mapper.toDomain(reservationToUpdated)
    }

    fun approveReservation(id: Long?): ResponseReservationDto? {
        val searchReservationToEntity = findReservationById(id)
        val reservationToUpdated = mapper.toEntity(searchReservationToEntity)

        if (reservationToUpdated.status != ReservationStatus.PENDING) {
            throw IllegalStateException("Невозможно изменить статус бронирование = ${reservationToUpdated.status}")
        }

        reservationToUpdated.status = ReservationStatus.APPROVED
        repo.save(reservationToUpdated)

        return mapper.toDomain(reservationToUpdated)
    }
}
