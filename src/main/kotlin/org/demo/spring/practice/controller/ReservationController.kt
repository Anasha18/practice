package org.demo.spring.practice.controller

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
        @RequestBody reservation: RequestCreateReservationDto,
    ): ResponseEntity<Void> {
        log.info("Called createReservation")
        service.createReservation(reservation)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("{id}")
    fun getReservationById(
        @PathVariable id: Long,
    ): ResponseEntity<ResponseReservationDto> {
        log.info("Called getReservationsById")
        return ResponseEntity
            .ok(service.findReservationById(id))
    }

    @GetMapping
    fun findAllReservations(): ResponseEntity<List<ResponseReservationDto>> {
        log.info("Called findAll")
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping("/{id}/cancel")
    fun cancelReservation(
        @PathVariable id: Long,
    ): ResponseEntity<ResponseReservationDto> {
        log.info("Called cancelReservation")
        return ResponseEntity.ok(service.cancelReservation(id))
    }
}
