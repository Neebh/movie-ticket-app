package com.example.movie.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.model.Screen;
import com.example.movie.model.Theater;


@Repository
public interface ScreenRepository extends JpaRepository<Screen, UUID>{
    
    public List<Screen> findByTheater(Theater theate);
}
