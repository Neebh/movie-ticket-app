package com.example.movie.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.model.Screen;
import com.example.movie.model.Theater;
import com.example.movie.repository.ScreenRepository;




@RestController
@RequestMapping(value = "/api")
public class ScreenController {
    
    @Autowired
    private ScreenRepository screenRepository;

    @GetMapping("/screen/{theaterId}")
    public ResponseEntity<List<Screen>> getScreens(@PathVariable("theaterId") String theaterId) {
        Theater theater = new Theater();
        theater.setId(UUID.fromString(theaterId));
        List<Screen> screens = screenRepository.findByTheater(theater);
        return ResponseEntity.ok(screens);
        
    }



}
