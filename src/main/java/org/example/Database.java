package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static String[] search;
    public static String subject="";
    public static List <String> custumerCID=new ArrayList<>();


    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

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


    public static List<Date> getDateEvents()
    {
        List<Date> date =new ArrayList<>();
       int count =0 ;


        String sql = "SELECT * FROM software2024.\"Events\"  " ;
        try
        {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean c =true;

            while (c)
            {
                if (rs.next())
                    count++;
                else
                    c=false;

            }



            System.out.println(count);

             rs = stmt.executeQuery(sql);

            for(int i =0;i<count;i++)
                if (rs.next()) {
                    date.add(rs.getDate("EventDate"));
                    custumerCID.add(rs.getString("CID"));

                }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


return date;

    }


    public static String getgmailReminder(String id )
    {

        String gml=new String ();
        String sql = "SELECT * FROM software2024.\"customer\" WHERE \"CID\" ='"+id+"'" ;
        try
        {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
                gml = rs.getString("GMAIL");


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

             return gml ;

    }





    public static boolean AddOrg(String ID, String name, String address, String gmail, String phone , String pass)
    {
        String sql = "INSERT INTO software2024.\"organizer\" (\"OID\", \"PHONENUMBER\", \"ADDRESS\", \"GMAIL\", \"USERNAME\", \"PASSWORD\") VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,ID);
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





    public static boolean DeleteOrg(String ID)
    {
        String sql = "DELETE FROM software2024.\"organizer\" WHERE \"OID\" ="+"'"+ ID+"'" ;
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




    public static String GetParticipantMessageTicket()
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



        try
        {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs ;

            if(cid==0)
                System.out.println("error in the id ");

            else {
                //tect the ctatuce of event done or not
                sql = "SELECT * FROM software2024.\"Events\"  WHERE \"CID\" ='" + cid + "'";
                rs = stmt.executeQuery(sql);


                if (rs.next())
                {
                    subject = rs.getString("EventName");

                    message = "Hello\n" +
                            "We invite you to attend my private party on " + rs.getString("EventDate") + " at " + rs.getString("EventTime") + "\n" +
                            "the location is " + rs.getString("Location") + ".\n" +
                            "Your presence is an honor for us, and may you be well";


                }
                else
                    System.out.println("error in massage!");

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

    public static boolean AddVenue(String VenueID, String VenueName, String Location, String Capacity, String Pricing)
    {
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
    public static boolean DeleteVenue(String VenueID)
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
