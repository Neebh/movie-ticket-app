package com.example.movie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Sku extends SuperEntity{

    @NonNull
    private String sku;

    @Enumerated(EnumType.STRING)
    @NonNull
    private SkuType type;

    @NonNull
    private Double price;



}
