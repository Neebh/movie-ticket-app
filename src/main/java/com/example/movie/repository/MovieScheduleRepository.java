package com.example.movie.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.model.MovieSchedule;

@Repository
public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, UUID>{



    
} 