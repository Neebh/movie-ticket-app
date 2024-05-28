package com.example.movie.model;

import jakarta.persistence.Entity;
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
public class Movie extends SuperEntity {

    @NonNull
    private  String name;
    
    @NonNull
    private String description;

    
}
