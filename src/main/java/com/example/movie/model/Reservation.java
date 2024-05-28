package com.example.movie.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reservation extends SuperEntity{

    private String reservationToken;

    private String userToken;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @OneToMany(mappedBy = "reservation", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<ReservationItem> reservationItems;


    public void addReservationItem(ReservationItem item) {
        if(reservationItems==null) {
            reservationItems = new ArrayList<>();
        }

        reservationItems.add(item);
        item.setReservation(this);
    }


 
}
