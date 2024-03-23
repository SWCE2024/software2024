package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static String[] search;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private static String userID;

    public static String getUserID() {
        String id=userID;
        return userID ;
    }

    public static void setUserID(String ID) {
        userID = ID;
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static List<String> fetchParticipantEmails() {
        List<String> emails = new ArrayList<>();
        String sql = "SELECT \"GMAIL\" FROM software2024.\"customer\"";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                emails.add(rs.getString("GMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public static boolean validateLogin(String email, String password, String table) {
        String sql = "SELECT * FROM software2024.\"" + table + "\" WHERE \"GMAIL\" = '" + email + "' AND \"PASSWORD\" = '" + password + "'";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registerCustomer(String id, String phoneNumber, String address, String gmail, String userName, String password) {
        String sql = "INSERT INTO software2024.\"customer\" (\"CID\", \"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\") VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, address);
            pstmt.setString(4, gmail);
            pstmt.setString(5, userName);
            pstmt.setString(6, password);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean AddVenue(String VenueID, String VenueName, String Location, String Capacity, String Pricing) {
        String sql = "INSERT INTO software2024.\"Venue\" (\"VenueID\", \"VenueName\", \"Location\", \"Capacity\", \"Pricing\") VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, VenueID);
            pstmt.setString(2, VenueName);
            pstmt.setString(3, Location);
            pstmt.setString(4, Capacity);
            pstmt.setString(5, Pricing);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    public static boolean DeleteVenue(String VenueID) {
        String sql = "DELETE FROM software2024.\"Venue\" WHERE \"VenueID\" ="+"'"+ VenueID+"'" ;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.executeUpdate();
             return true;
        }

        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
}
