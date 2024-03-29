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
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";
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
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        } catch (Exception e) {
            e.printStackTrace();
        }return conn;
    }
    public static List<Date> getDateEvents()
    {
        List<Date> date =new ArrayList<>();
       int count =0 ;
        String sql = "SELECT \"EventID\" FROM software2024.\"Events\"  " ;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean c =true;
            while (c)
            {
                if (rs.next()) count++;
                else c=false;
            }

             rs = stmt.executeQuery(sql);
            for(int i =0;i<count;i++)
                if (rs.next()) {
                    date.add(rs.getDate("EventDate"));
                    custumerCID.add(rs.getString("CID"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String getgmailReminder(String id )
    {
        String gml="";
        String sql = "SELECT \"GMAIL\" FROM software2024.\"customer\" WHERE \"CID\" ='"+id+"'" ;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
                gml = rs.getString("GMAIL");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }return gml ;
    }
    public static boolean addOrg(String iD, String name, String address, String gmail, String phone , String pass)
    {
        String sql = "INSERT INTO software2024.\"organizer\" (\"OID\", \"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\") VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,iD);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setString(4, gmail);
            pstmt.setString(5, name);
            pstmt.setString(6, pass);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteOrg(String iD)
    {
        String sql = "DELETE FROM software2024.\"organizer\" WHERE \"OID\" ="+"'"+ iD+"'" ;
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
    public static String getParticipantMessageTicket()
    {
        String  message= "";
        int cid=0;
        String sql = "SELECT \"CID\" FROM software2024.\"customer\"  WHERE \"GMAIL\" ="+"'"+ HelloController.getEmail()+"'" ;
        try
        {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
                cid = rs.getInt("CID");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        try {
            Logger logger = Logger.getLogger(Database.class.getName());

            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs ;
            if(cid==0)
                logger.info("error in the id");

            else {
                sql = "SELECT * FROM software2024.\"Events\"  WHERE \"CID\" ='" + cid + "'";
                rs = stmt.executeQuery(sql);
                if (rs.next())
                {
                    setSubject(rs.getString("EventName"));
                    message = "Hello\n" +
                            "We invite you to attend my private party on " + rs.getDate("EventDate") + " at " + rs.getTime("EventTime") + "\n" +
                            "the location is " + rs.getString("Location") + ".\n" +
                            "Your presence is an honor for us, and may you be well";
                }
                else
                    logger.log(Level.SEVERE, "error in massage!", e);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return message;
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

    public static boolean addVenue(String venueID, String venueName, String location, String capacity, String pricing)
    {
        String sql = "INSERT INTO software2024.\"Venue\" (\"VenueID\", \"VenueName\", \"Location\", \"Capacity\", \"Pricing\") VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, venueID);
            pstmt.setString(2, venueName);
            pstmt.setString(3, location);
            pstmt.setString(4, capacity);
            pstmt.setString(5, pricing);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
    public static boolean deleteVenue(String VenueID)
    {
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
