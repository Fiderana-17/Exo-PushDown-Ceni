package org.example;

import org.example.Config.DBConnection;
import org.example.Entity.CandidateVoteCount;
import org.example.Entity.VoteTypeCount;
import org.example.Service.DataRetriever;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();

        try (Connection connection = dbConnection.getConnection()) {

            DataRetriever dataRetriever = new DataRetriever();

            System.out.println("---- Q1 : Nombre total de votes ----");

            long totalVote = dataRetriever.countAllVotes(connection);

            System.out.println("totalVote = " + totalVote);

            System.out.println("---- Q2 : Votes par type ----");

            List<VoteTypeCount> results =
                    dataRetriever.countVotesByType(connection);

            results.forEach(v ->
                    System.out.println(
                            v.getVoteType() + " | " + v.getCount()
                    )
            );

            System.out.println("=== Question 3 - Votes valides par candidat ===");
            List<CandidateVoteCount> candidateVotes = dataRetriever.countValidVotesByCandidate(connection);
            System.out.print("Résultat: [");
            for (int i = 0; i < candidateVotes.size(); i++) {
                System.out.print(candidateVotes.get(i));
                if (i < candidateVotes.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
};