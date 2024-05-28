package com.example.movie.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID>{
    

    
}
