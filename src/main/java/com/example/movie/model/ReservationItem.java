package com.example.movie.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity()
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// @Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueMovieScheduleAndSeat", columnNames = { "schedule_id", "seat_id" }) })
public class ReservationItem extends SuperEntity{

    @ManyToOne
    @JoinColumn(name="schedule_id", nullable=false)
    @JsonIgnore
    private MovieSchedule movieSchedule;


    @ManyToOne
    @JoinColumn(name="seat_id", nullable=false)
    @JsonIgnore
    private Seat seat;

    @ManyToOne
    @JoinColumn(name="reservation_id", nullable=false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name="sku_id", nullable=false)
    @JsonIgnore
    private Sku sku;


}
