package com.example.movie.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.movie.model.Screen;
import com.example.movie.model.Seat;
import com.example.movie.model.SeatType;

import io.github.cdimascio.dotenv.Dotenv;

public class ImportSeats {
    
    private static final String COMMA_DELIMITER =",";
    private static final int BATCH_SIZE = 1000;


    public static void main(final String args[]) throws FileNotFoundException, IOException {

        String userDirectory = FileSystems.getDefault()
        .getPath("")
        .toAbsolutePath()
        .toString();

        Dotenv dotenv = Dotenv.load();
        String URL = dotenv.get("URL");
        String USER = dotenv.get("USER");
        String PASSWORD = dotenv.get("PASSWORD");
        
        List<Seat> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(userDirectory + "/" + "seats.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Seat seat = new Seat(values[0], SeatType.valueOf(values[1]));
                Screen screen = new Screen("test",24);
                screen.setId(UUID.fromString(values[2]));
                seat.setScreen(screen);
                records.add(seat);
            }
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
    

        String insertSQL = "INSERT INTO SEAT (id, seat_number, type, screen_id, created_date, updated_date) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(insertSQL);
            connection.setAutoCommit(false);

            int parameterIndex = 1;
            int j=0;
            for (Seat seat : records) {
                    preparedStatement.setObject(parameterIndex, UUID.randomUUID());
                    preparedStatement.setString(++parameterIndex, seat.getSeatNumber());
                    preparedStatement.setString(++parameterIndex, seat.getType().name());
                    preparedStatement.setObject(++parameterIndex, seat.getScreen().getId());
                    preparedStatement.setTimestamp(++parameterIndex, Timestamp.valueOf(LocalDateTime.now()));
                    preparedStatement.setTimestamp(++parameterIndex, Timestamp.valueOf(LocalDateTime.now()));
                
                    parameterIndex =1;
                    preparedStatement.addBatch();
                    if(j%BATCH_SIZE==0) {
                        preparedStatement.executeBatch();
                        connection.commit();
                    }
                    j++;
            }
            
            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
