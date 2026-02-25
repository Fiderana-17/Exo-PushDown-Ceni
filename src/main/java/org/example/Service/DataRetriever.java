package org.example.Service;

import org.example.Entity.CandidateVoteCount;
import org.example.Entity.ElectionResult;
import org.example.Entity.VoteSummary;
import org.example.Entity.VoteTypeCount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public long countAllVotes(Connection connection) throws SQLException {

        String sql = "SELECT COUNT(*) AS total_votes FROM vote";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getLong("total_votes");
            }
        }

        return 0;
    }

    public List<VoteTypeCount> countVotesByType(Connection connection) throws SQLException {

        String sql = """
        SELECT vote_type, COUNT(*) AS count
        FROM vote
        GROUP BY vote_type
        ORDER BY vote_type
    """;

        List<VoteTypeCount> results = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                results.add(new VoteTypeCount(
                        rs.getString("vote_type"),
                        (int) rs.getLong("count")
                ));
            }
        }

        return results;
    }

    public List<CandidateVoteCount> countValidVotesByCandidate(Connection connection) throws SQLException {
        String sql = "SELECT c.name AS candidate_name, " +
                "COUNT(CASE WHEN v.vote_type = 'VALID' THEN 1 END) AS valid_vote " +
                "FROM candidate c " +
                "LEFT JOIN vote v ON c.id = v.candidate_id " +
                "GROUP BY c.id, c.name " +
                "ORDER BY c.id";

        List<CandidateVoteCount> results = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String candidateName = rs.getString("candidate_name");
                int validVoteCount = rs.getInt("valid_vote");
                results.add(new CandidateVoteCount(candidateName, validVoteCount));
            }
        }

        return results;
    }

    public VoteSummary computeVoteSummary(Connection connection) throws SQLException {
        String sql = "SELECT " +
                "COUNT(CASE WHEN vote_type = 'VALID' THEN 1 END) AS valid_count, " +
                "COUNT(CASE WHEN vote_type = 'BLANK' THEN 1 END) AS blank_count, " +
                "COUNT(CASE WHEN vote_type = 'NULL' THEN 1 END) AS null_count " +
                "FROM vote";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int validCount = rs.getInt("valid_count");
                int blankCount = rs.getInt("blank_count");
                int nullCount = rs.getInt("null_count");
                return new VoteSummary(validCount, blankCount, nullCount);
            }
        }

        return new VoteSummary(0, 0, 0);
    }

    public double computeTurnoutRate(Connection connection) throws SQLException {
        String sql = "SELECT " +
                "(SELECT COUNT(*) FROM vote) * 100.0 / " +
                "(SELECT COUNT(*) FROM voter) AS turnout_rate";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("turnout_rate");
            }
        }

        return 0.0;
    }

    public static ElectionResult findWinner(Connection connection) throws SQLException {
        String sql = "SELECT c.name AS candidate_name, " +
                "COUNT(v.id) AS valid_vote_count " +
                "FROM candidate c " +
                "LEFT JOIN vote v ON c.id = v.candidate_id " +
                "AND v.vote_type = 'VALID' " +
                "GROUP BY c.id, c.name " +
                "ORDER BY valid_vote_count DESC " +
                "LIMIT 1";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                String candidateName = rs.getString("candidate_name");
                int validVoteCount = rs.getInt("valid_vote_count");
                return new ElectionResult(candidateName, validVoteCount);
            }
        }

        return new ElectionResult("No winner", 0);
    }
}
