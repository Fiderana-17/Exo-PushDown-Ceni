package org.example;

import org.example.Config.DBConnection;
import org.example.Service.DataRetriever;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();

        try (Connection connection = dbConnection.getConnection()) {

            DataRetriever dataRetriever = new DataRetriever();

            System.out.println("---- Q1 : Nombre total de votes ----");

            long totalVote = dataRetriever.countAllVotes(connection);

            System.out.println("totalVote = " + totalVote);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
};