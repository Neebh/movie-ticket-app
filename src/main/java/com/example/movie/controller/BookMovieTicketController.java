package com.example.movie.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.request.BookMovieRequest;
import com.example.movie.service.BookTicketService;

@RestController

@RequestMapping(value = "/api")
public class BookMovieTicketController {
    

    @Autowired
    private BookTicketService bookTicketService;


    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(@RequestBody BookMovieRequest request) {
        bookTicketService.bookTicket(request);
        return ResponseEntity.ok("success");
    }

}
