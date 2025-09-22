package org.demo.spring.practice.exception

import jakarta.persistence.EntityNotFoundException
import org.demo.spring.practice.controller.ReservationController
import org.demo.spring.practice.exception.dto.ErrorResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {
    private val log: Logger = LoggerFactory.getLogger(ReservationController::class.java)

    @ExceptionHandler(Exception::class)
    fun handleGeneric(e: Exception): ResponseEntity<ErrorResponseDto> {
        logging(e)

        val errorDto =
            ErrorResponseDto(
                message = "INTERNAL_SERVER_ERROR",
                detailMessage = e.message.toString(),
                errorTime = LocalDateTime.now(),
            )

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorDto)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(e: EntityNotFoundException): ResponseEntity<ErrorResponseDto> {
        logging(e)

        val errorDto =
            ErrorResponseDto(
                message = "NOT_FOUND",
                detailMessage = e.message.toString(),
                errorTime = LocalDateTime.now(),
            )

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(errorDto)
    }

    @ExceptionHandler(
        IllegalArgumentException::class,
        IllegalStateException::class,
        MethodArgumentNotValidException::class,
    )
    fun handleBadRequest(e: Exception): ResponseEntity<ErrorResponseDto> {
        logging(e)

        val errorDto =
            ErrorResponseDto(
                message = "BAD_REQUEST",
                detailMessage = e.message.toString(),
                errorTime = LocalDateTime.now(),
            )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorDto)
    }

    private fun logging(e: Any) = log.error("Вылетело исключение: $e")
}
