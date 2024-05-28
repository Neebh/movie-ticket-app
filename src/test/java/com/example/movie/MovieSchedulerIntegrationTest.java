package com.example.movie;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.example.movie.model.Movie;
import com.example.movie.model.MovieSchedule;
import com.example.movie.model.Screen;
import com.example.movie.model.Seat;
import com.example.movie.model.SeatType;
import com.example.movie.model.Theater;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class MovieSchedulerIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DirtiesContext 
    @Transactional
    public void insertSchedule() {

        Movie movie = new Movie("12th Fail", "blah blah");
        entityManager.persist(movie);
        Seat seat = new Seat("A1", SeatType.NORMAL);
        Seat seat1 = new Seat("A2", SeatType.NORMAL);
        Seat seat2 = new Seat("A3", SeatType.NORMAL);
        Seat seat3 = new Seat("A4", SeatType.HANDICAP);
        Seat seat4 = new Seat("A5", SeatType.NORMAL);

        Screen screen = new Screen("Screen 1", 200);
        screen.addSeat(seat);
        screen.addSeat(seat1);
        screen.addSeat(seat2);
        screen.addSeat(seat3);
        screen.addSeat(seat4);
     
        Theater theater = new Theater( "AMC North Point Mall", "atlanta", "300005");
        theater.addScreen(screen);

        entityManager.persist(theater);

        Theater fromDb = entityManager.find(Theater.class, theater.getId());
        System.out.println(fromDb.getScreens());
        System.out.println(fromDb.getScreens().get(0).getSeats());
        LocalDateTime startTime = LocalDateTime.of(2024, 5, 24, 11, 30, 0);

        LocalDateTime enDateTime = LocalDateTime.of(2024, 5, 24, 3, 30, 0);
        
        MovieSchedule movieSchedule = new MovieSchedule(startTime, enDateTime);
        movieSchedule.setMovie(movie);
        movieSchedule.setScreen(screen);

        LocalDateTime secondStartTime = LocalDateTime.of(2024, 5, 24, 2, 30, 0);
        LocalDateTime secondEndTime = LocalDateTime.of(2024, 5, 24, 4, 30, 0);

        MovieSchedule secondSchedule = new MovieSchedule(secondStartTime, secondEndTime);
        secondSchedule.setMovie(movie);
        secondSchedule.setScreen(screen);


        entityManager.persist(movieSchedule);
        entityManager.persist(secondSchedule);

        
    }
    
    
}
