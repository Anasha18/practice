package org.demo.spring.practice.controller

import jakarta.validation.Valid
import org.demo.spring.practice.controller.dto.RequestCreateReservationDto
import org.demo.spring.practice.controller.dto.ResponseReservationDto
import org.demo.spring.practice.service.ReservationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/reservations")
class ReservationController(
    val service: ReservationService,
) {
    private val log: Logger = LoggerFactory.getLogger(ReservationController::class.java)

    @PostMapping
    fun createReservation(
        @RequestBody @Valid reservationToCreate: RequestCreateReservationDto,
    ): ResponseEntity<ResponseReservationDto> {
        log.info("Called createReservation: $reservationToCreate")
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReservation(reservationToCreate))
    }

    @GetMapping("{id}")
    fun getReservationById(
        @PathVariable id: Long,
    ): ResponseEntity<ResponseReservationDto> {
        log.info("Called getReservationsById = $id")
        return ResponseEntity.ok(service.findReservationById(id))
    }

    @GetMapping
    fun findAllReservations(): ResponseEntity<List<ResponseReservationDto>> {
        log.info("Called findAllReservations")
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping("/{id}/cancel")
    fun cancelReservation(
        @PathVariable id: Long,
    ): ResponseEntity<ResponseReservationDto> {
        log.info("Called cancelReservation: $id")
        return ResponseEntity.ok(service.cancelReservation(id))
    }

    @PostMapping("/{id}/approve")
    fun approveReservation(
        @PathVariable id: Long,
    ): ResponseEntity<ResponseReservationDto> {
        log.info("Called approveReservation: $id")
        return ResponseEntity.ok(service.approveReservation(id))
    }
}
