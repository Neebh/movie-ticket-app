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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.movie.model.Movie;
import com.example.movie.model.MovieSchedule;
import com.example.movie.model.Screen;

import io.github.cdimascio.dotenv.Dotenv;

public class ImportSchedule {
    
    private static final String COMMA_DELIMITER =",";
    private static final int BATCH_SIZE = 1000;


    public static void main(final String args[]) throws FileNotFoundException, IOException, ParseException {

        String userDirectory = FileSystems.getDefault()
        .getPath("")
        .toAbsolutePath()
        .toString();
        System.out.println(userDirectory);

        Dotenv dotenv = Dotenv.load();
        String URL = dotenv.get("URL");
        String USER = dotenv.get("USER");
        String PASSWORD = dotenv.get("PASSWORD");
        
        List<MovieSchedule> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(userDirectory + "/" + "schedule.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Date starDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").parse(values[2]);
                Date endDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").parse(values[3]);
                MovieSchedule schedule = new MovieSchedule(starDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() );
                Screen screen = new Screen("test",24);
                screen.setId(UUID.fromString(values[1]));
                schedule.setScreen(screen);

                Movie movie = new Movie("test","");
                movie.setId(UUID.fromString(values[0]));
                schedule.setMovie(movie);
                records.add(schedule);
            }
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
    

        String insertSQL = "INSERT INTO MOVIE_SCHEDULE (id, movie_id, screen_id, start_time, end_time, created_date, updated_date) VALUES (?, ?, ?, ?, ?,?,?)";

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(insertSQL);
            connection.setAutoCommit(false);

            int parameterIndex = 1;

            int j=0;
            for (MovieSchedule schedule : records) {
                    preparedStatement.setObject(parameterIndex, UUID.randomUUID());
                    preparedStatement.setObject(++parameterIndex, schedule.getMovie().getId());
                    preparedStatement.setObject(++parameterIndex, schedule.getScreen().getId());
                    preparedStatement.setTimestamp(++parameterIndex, Timestamp.valueOf(schedule.getStartTime()));
                    preparedStatement.setTimestamp(++parameterIndex, Timestamp.valueOf(schedule.getEndTime()));
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
