package com.example.movie.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Theater extends SuperEntity {


    @NonNull
    private String name;
    @NonNull
    private String city;
    @NonNull
    private String zipCode;

    @OneToMany(mappedBy="theater", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    List<Screen> screens;
    
    public void addScreen(Screen screen){

        if(screens==null) {
            screens = new ArrayList<Screen>();
        }

        screens.add(screen);
        screen.setTheater(this);
    }
    
}
