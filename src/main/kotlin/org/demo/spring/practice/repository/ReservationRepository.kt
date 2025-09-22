package org.demo.spring.practice.repository

import org.demo.spring.practice.controller.dto.ResponseReservationDto
import org.demo.spring.practice.model.Reservation
import org.demo.spring.practice.model.ReservationStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository : JpaRepository<Reservation, Long> {
    fun findReservationById(id: Long?): Reservation?

    @Query(
        """
        update Reservation r
        set r.status = :status 
        where r.id = :id
        """,
    )
    @Modifying
    fun updateStatusReservation(
        @Param("id") id: Long?,
        @Param("status") status: ReservationStatus?,
    )
}
