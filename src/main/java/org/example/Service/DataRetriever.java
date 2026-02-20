package org.example.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
