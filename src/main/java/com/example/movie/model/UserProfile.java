package com.example.movie.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class UserProfile extends SuperEntity {


    private String username;
    private String email;
    private String firstName;
    private String lastName;

    
}
