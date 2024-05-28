package com.example.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.exception.NotFoundException;
import com.example.movie.model.MovieSchedule;
import com.example.movie.model.Reservation;
import com.example.movie.model.ReservationItem;
import com.example.movie.model.ReservationStatus;
import com.example.movie.model.Seat;
import com.example.movie.model.Sku;
import com.example.movie.repository.MovieScheduleRepository;
import com.example.movie.repository.ReservationItemRepository;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.repository.SeatRepository;
import com.example.movie.repository.SkuRepository;
import com.example.movie.request.BookMovieRequest;
import com.example.movie.request.SeatRequest;


@Service
public class BookTicketService {
    

    @Autowired
    private ReservationItemRepository reservationItemRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MovieScheduleRepository movieScheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired SkuRepository skuRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public void bookTicket(BookMovieRequest request) {

        Optional<MovieSchedule> movieSchedule = movieScheduleRepository.findById(UUID.fromString(request.getScheduleId()));

        if(!movieSchedule.isPresent()) {
            throw new NotFoundException("No Schedule exists");
        }

      
        List<UUID> seatUUID  = new ArrayList<>();
        List<UUID> sUuids  = new ArrayList<>();
        for(SeatRequest seatRequest: request.getSeat()) {
            seatUUID.add(UUID.fromString(seatRequest.getSeatUuid()));
            sUuids.add(UUID.fromString(seatRequest.getSkUuid()));
        }


       List<Seat> seats =  seatRepository.findAllById(seatUUID);

       if(seats==null || seats.isEmpty() || seats.size()!=seatUUID.size()) {
            throw new NotFoundException("Seats Not Found");
       }


       List<Sku> skus =  skuRepository.findAllById(sUuids);

       Map<UUID, Seat> seatUUIdToSeat = seats.stream().collect(Collectors.toMap(Seat::getId, seat -> seat));

       Map<UUID, Sku> skuUUIdToSKu = skus.stream().collect(Collectors.toMap(Sku::getId, sku -> sku));

        List<ReservationItem> items = reservationItemRepository.findItemsByMovieSchedule(movieSchedule.get().getId(), seatUUID); 

        if(items==null || items.isEmpty()) {
            Reservation reservation = new Reservation();
            for(SeatRequest seatRequest: request.getSeat()) {
                ReservationItem reservationItem = new ReservationItem();
                reservationItem.setSeat(seatUUIdToSeat.get(UUID.fromString(seatRequest.getSeatUuid())));
                reservationItem.setSku(skuUUIdToSKu.get(UUID.fromString(seatRequest.getSkUuid())));
                reservationItem.setMovieSchedule(movieSchedule.get());
                reservation.addReservationItem(reservationItem);
            }
            reservation.setStatus(ReservationStatus.RESERVED);
            reservation.setUserToken(request.getUserEmail());
            reservation.setReservationToken(UUID.randomUUID().toString());
            reservationRepository.save(reservation);

        }else{
            throw new NotFoundException("Ticket not available Retry with different seats");
        }

    }

}
