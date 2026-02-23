package org.example.Service;

import org.example.Entity.VoteTypeCount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
