package com.example.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat extends SuperEntity {
  
  
    @NonNull
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @NonNull
    private SeatType type;

    @ManyToOne
    @JoinColumn(name="screen_id", nullable=false)
    @JsonIgnore
    private Screen screen;

}
