package com.example.movie.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Screen extends SuperEntity {

    @NonNull
    private String name;

    @NonNull
    private Integer capacity;
    
    @ManyToOne
    @JoinColumn(name="theater_id", nullable=false)
    @JsonIgnore
    private Theater theater;

    @OneToMany(mappedBy = "screen", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Seat> seats;


    public void addSeat(Seat seat) {
        if(seats==null) {
            seats = new ArrayList<>();
        }
        seats.add(seat);
        seat.setScreen(this);
    }


}
