package com.example.movie.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovieSchedule extends SuperEntity
 {
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "screen_id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(nullable = false, name = "movie_id")
    private Movie movie;

    @Column(columnDefinition = "TIMESTAMP")
    @NonNull
    private LocalDateTime startTime;

    @Column(columnDefinition = "TIMESTAMP")
    @NonNull
    private LocalDateTime endTime;


}
