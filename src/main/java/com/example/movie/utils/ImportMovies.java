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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import io.github.cdimascio.dotenv.Dotenv;

public class ImportMovies {
    
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
        
        
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(userDirectory + "/" + "movies.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
    

        String insertSQL = "INSERT INTO Movie (id, name, description, created_date, updated_date) VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(insertSQL);
            connection.setAutoCommit(false);

            int parameterIndex = 1;
            for (int j = 0; j < records.size(); j++) {
                    List<String> record = records.get(j);

                    preparedStatement.setObject(parameterIndex, UUID.randomUUID());
                    for(String ss: record) {
                        preparedStatement.setString(++parameterIndex, ss);
                    }
                    preparedStatement.setTimestamp(++parameterIndex, Timestamp.valueOf(LocalDateTime.now()));
                    preparedStatement.setTimestamp(++parameterIndex, Timestamp.valueOf(LocalDateTime.now()));
                    System.out.println(preparedStatement);
                    parameterIndex =1;
                    preparedStatement.addBatch();
                    if(j%BATCH_SIZE==0) {
                        preparedStatement.executeBatch();
                        connection.commit();
                    }
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
