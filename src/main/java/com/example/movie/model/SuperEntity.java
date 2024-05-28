package com.example.movie.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
public class SuperEntity implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime updatedDate;

    @UpdateTimestamp
    private LocalDateTime createdDate;


}
