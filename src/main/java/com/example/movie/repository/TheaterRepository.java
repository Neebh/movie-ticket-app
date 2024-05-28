package com.example.movie.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.model.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, UUID>{
    
    public List<Theater> findByZipCode(String zipCode);
    public List<Theater> findByCity(String city);
}
