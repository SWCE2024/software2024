package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.SignUpController.logger;



public class Database {
    private static String e="message";
    private Database() {
        throw new IllegalStateException("Utility class");
    }

    private static String[] search;
    private static String subject = "";

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String newSubject) {
        subject = newSubject;
    }
    static List<String> custumerCID = new ArrayList<>();
    public static void clearCustomerCID() {
        custumerCID.clear();
    }
    public static boolean removeCustomerCID(String cid) {
        return custumerCID.remove(cid);
    }

    public static String[] getSearch() {
        return search;
    }
    public static void setSearch(String[] search) {
        Database.search = search;
    }
    public static List<String> getCustomerCID() {
        return custumerCID;
    }
    public static void addCustomerCID(String cid) {
        custumerCID.add(cid);
    }
    private static String userID;
    public static String getUserID() {
        return userID ;
    }
    public static void setUserID(String id) {

        userID = id;
    }
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            if (url == null || user == null || password == null) {
                logger.log(Level.SEVERE, "Database connection details are missing in environment variables.");
                return null;
            }
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "An error occurred while trying to connect to the database:", e.getMessage());
            logger.info(e.toString());
        }
        return conn;
    }


    public static List<Date> getDateEvents() throws SQLException
    {
        List<Date> date =new ArrayList<>();
        int count =0 ;
        String sql = "SELECT \"EventID\" FROM software2024.\"Events\"  " ;



        try(  Connection conn = connect()) {
            assert conn != null;
            try(Statement  stmt = conn.createStatement())
            {
                ResultSet rs = stmt.executeQuery(sql);
                boolean c = true;
                while (c) {
                    if (rs.next()) count++;
                    else c = false;
                }

                rs = stmt.executeQuery(sql);
                for (int i = 0; i < count; i++)
                    if (rs.next())
                    {
                        date.add(rs.getDate("EventDate"));
                        custumerCID.add(rs.getString("CID"));
                    }

            }
        }
        catch (SQLException | NullPointerException e)
        {
            logger.info(e.toString());
        }


        return date;
    }
    public static String getgmailReminder(String id) throws SQLException {
        String gml = "";
        // Using a parameterized query to prevent SQL Injection
        String sql = "SELECT \"GMAIL\" FROM software2024.\"customer\" WHERE \"CID\" = ?";

        try (Connection conn = connect();
             // Create a PreparedStatement from the connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Ensure the connection is not null
            assert conn != null;
            // Set the parameter (id) in the query
            pstmt.setString(1, id);
            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    gml = rs.getString("GMAIL");
                }
            }
        } catch (SQLException | NullPointerException e) {
            logger.info(e.toString());
        }

        return gml;
    }

    public static boolean addOrg(String iD, String name, String address, String gmail, String phone , String pass)
    {
        String sql = "INSERT INTO software2024.\"organizer\" (\"OID\", \"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\") VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1,iD);
                pstmt.setString(2, phone);
                pstmt.setString(3, address);
                pstmt.setString(4, gmail);
                pstmt.setString(5, name);
                pstmt.setString(6, pass);

                int affectedRows = pstmt.executeUpdate();
                pstmt.close();
                return affectedRows > 0;
            }
        }
        catch (SQLException |NullPointerException e) {
            logger.info(e.toString());
            return false;
        }
    }
    public static boolean deleteOrg(String iD) {
        String sql = "DELETE FROM software2024.\"organizer\" WHERE \"OID\" = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            assert conn != null;
            pstmt.setString(1, iD);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.info(e.toString());
            return false;
        }
    }

    public static List<String> fetchParticipantEmails() {
        List<String> emails = new ArrayList<>();
        String sql = "SELECT \"GMAIL\" FROM software2024.\"customer\"";
        try (Connection conn = connect()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    emails.add(rs.getString("GMAIL"));
                }

            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return emails;
    }
    public static String getParticipantMessageTicket() {
        String message = "";
        // Initialize cid as null to handle cases where the email does not correspond to a customer
        Integer cid = null;
        // Use parameterized queries to prevent SQL Injection
        String getEmailSql = "SELECT \"CID\" FROM software2024.\"customer\" WHERE \"GMAIL\" = ?";
        String getEventSql = "SELECT * FROM software2024.\"Events\" WHERE \"CID\" = ?";

        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(getEmailSql)) {
                pstmt.setString(1, HelloController.getEmail());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        cid = rs.getInt("CID");
                    }
                }
            }
            if (cid != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(getEventSql)) {
                    pstmt.setInt(1, cid);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            setSubject(rs.getString("EventName"));
                            message = "Hello\n" +
                                    "We invite you to attend my private party on " + rs.getDate("EventDate") + " at " + rs.getTime("EventTime") + "\n" +
                                    "the location is " + rs.getString("Location") + ".\n" +
                                    "Your presence is an honor for us, and may you be well";
                        } else {
                            logger.log(Level.SEVERE, "error in message!");
                        }
                    }
                }
            } else {
                logger.log(Level.SEVERE, "Error in the ID");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred while executing the query", e);
        }
        return message;
    }


    public static boolean validateLogin(String email, String password, String table) {
        String sql = "SELECT * FROM software2024.\"" + table + "\" WHERE \"GMAIL\" = ? AND \"PASSWORD\" = ?";
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while validating login", e);
        }
        return false;
    }



    public static boolean registerCustomer(String id, String phoneNumber, String address, String gmail, String userName, String password) {
        String sql = "INSERT INTO software2024.\"customer\" (\"CID\", \"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\") VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                pstmt.setString(2, phoneNumber);
                pstmt.setString(3, address);
                pstmt.setString(4, gmail);
                pstmt.setString(5, userName);
                pstmt.setString(6, password);

                int affectedRows = pstmt.executeUpdate();
                pstmt.close();
                return affectedRows > 0;

            }
        } catch (SQLException e) {
            logger.log(null,"An error ",e);
            return false;
        }
    }

    public static boolean addVenue(String venueID, String venueName, String location, String capacity, String pricing)
    {
        String sql = "INSERT INTO software2024.\"Venue\" (\"VenueID\", \"VenueName\", \"Location\", \"Capacity\", \"Pricing\") VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, venueID);
                pstmt.setString(2, venueName);
                pstmt.setString(3, location);
                pstmt.setString(4, capacity);
                pstmt.setString(5, pricing);

                int affectedRows = pstmt.executeUpdate();
                pstmt.close();
                return affectedRows > 0;
            }
        }
        catch (SQLException e) {
            logger.log(null,"An error ",e);
            return false;
        }


    }
    public static boolean deleteVenue(String venueID) {
        String sql = "DELETE FROM software2024.\"Venue\" WHERE \"VenueID\" = ?";
        try (Connection conn = connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, venueID);
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            logger.info(e.toString());
            return false;
        }
    }

}