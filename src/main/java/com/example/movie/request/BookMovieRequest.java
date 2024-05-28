package com.example.movie.request;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookMovieRequest {
    private List<SeatRequest> seat;
    private  String scheduleId;
    private String userEmail;

}

