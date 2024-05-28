package com.example.movie.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.movie.model.ReservationItem;

@Repository
public interface ReservationItemRepository extends JpaRepository<ReservationItem, UUID>{

    @Query("Select ri from ReservationItem ri where ri.movieSchedule.id=?1 and ri.seat.id in ?2")
    public List<ReservationItem> findItemsByMovieSchedule(UUID movieScheduleId, List<UUID> seats);

}
