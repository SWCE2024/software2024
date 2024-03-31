package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.example.SignUpController.logger;

public class DatabaseUtil {
    @FunctionalInterface
    public interface ResultSetProcessor<T> {
        T processRow(ResultSet rs) throws SQLException;
    }

    public static <T> List<T> executeQuery(String sql, ResultSetProcessor<T> processor) {
        List<T> results = new ArrayList<>();
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                results.add(processor.processRow(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        return results;
    }
}
