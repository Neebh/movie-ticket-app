package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.model.Theater;
import com.example.movie.repository.TheaterRepository;




@RestController
@RequestMapping(value = "/api")
public class TheaterController {
    
    @Autowired
    private TheaterRepository theaterRepository;

    @GetMapping("/theater/zip/{zipCode}")
    public ResponseEntity<List<Theater>> getTheaters(@PathVariable("zipCode") String zipCode) {
        List<Theater> theaters = theaterRepository.findByZipCode(zipCode);
        return ResponseEntity.ok(theaters);
        
    }

    @GetMapping("/theater/cit/{city}")
    public ResponseEntity<List<Theater>> getTheatersByCity(@PathVariable("city") String city) {
        List<Theater> theaters = theaterRepository.findByCity(city);
        return ResponseEntity.ok(theaters);
        
    }

  




}
