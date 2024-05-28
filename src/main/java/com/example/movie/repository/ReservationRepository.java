package com.example.movie.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>{

    
}
