package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;


public class TESTINPUT {
    private TESTINPUT(){

    }
    public static boolean emptyTest(String name) {
        if (name==null||name.isEmpty())
            return false;
        else return true;
    }

    public static boolean VenueNameTest(String name) {
      if (name==null||name.isEmpty())
          return false;
      else return true;
    }
    public static boolean VenueLocationTest(String Location) {
        if (Location==null||Location.isEmpty())
            return false;
        else return true;
    }

    public static boolean VenueCapacityTest(String Capacity) {
        int i = parseInt(Capacity);
        if (i<=0)
            return false;
        else return true;
    }

    public static boolean VenuePricingTest(String Pricing) {
        int i = parseInt(Pricing);
        if (i<=0)
            return false;
        else return true;


    }

    public static boolean idTest(String id) {
        if (id.length() == 9) {
            boolean flag = true;
            for (int i = 0; i < id.length(); i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
        return false;
    }

    public static boolean phoneNumberTest(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            boolean flag = true;
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
        return false;
    }

    public static boolean gmailTest(String gmail) {
        if (Character.isDigit(gmail.charAt(0)) || gmail.length() < 17) return false;
        else {
            boolean flag = false;
            for (int i = 1; i < gmail.length(); i++) {
                if (gmail.charAt(i) == '@') flag = true;
            }
            return flag;
        }
    }

    public static boolean passwordTest(String password) {
        boolean flags=false;
        boolean flagc=false;
        boolean flagn=false;
        if( password.length()<8) return false;
        else {
            for (int i = 0; i < password.length(); i++){
                if(Character.isLowerCase(password.charAt(i))) flags=true;
                else if(Character.isUpperCase(password.charAt(i))) flagc=true;
                else if (Character.isDigit(password.charAt(i))) flagn=true;
            }
            return flags&&flagc&&flagn;
        }
    }

    public static boolean dateTest(String Date){
        LocalDate enteredDate = parseUserInput(Date);

        // Check if the entered date is in the future
        if (enteredDate != null && isFutureDate(enteredDate)) {
           // System.out.println("The entered date is in the future.");
            return true;
        } else {
            System.out.println("Invalid date, the date should be in the future.");
            return false;
        }

    }

    public static boolean timeTest(String Time){
        if (isValidTime(Time)) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean countTest(String Attendecount){
        try {
            int count = Integer.parseInt(Attendecount);

            if (isValidCount(count)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return false;
        }

    }
    public static boolean pictureTest(String Picture){
        if (Picture.indexOf(".png")!= -1 || Picture.indexOf(".jpg")!= -1) return true;
        else
            return false;
    }

    public static boolean checkInputs(String phoneNumber, String price){
        boolean flag=false;

        if (!(phoneNumber.length()==10)) {
            System.out.println("Invalid phone number. Phone number should consist of 10 digits.");
            return false;
        }
        if (Integer.parseInt(price)<0) {
            System.out.println("Invalid price. Price should be greater than zero.");
            return false;
        }
        return true;
    }
   /* public static boolean checkExsistOrNot(int serviceProviderID) throws SQLException {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn =Database.connect();
            String sql = "SELECT COUNT(*) AS count FROM ServiceProviders WHERE ServiceProviderID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, serviceProviderID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                exists = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }*/
    private static boolean isValidCount(int count) {
        return count > 0;
    }

    private static boolean isValidTime(String inputTime) {
        try {
            LocalTime time = LocalTime.parse(inputTime);
            return time.isAfter(LocalTime.MIN) && time.isBefore(LocalTime.MAX);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static LocalDate parseUserInput(String userInput) {
        try {
            // Parse the user input using the specified date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(userInput, formatter);
        } catch (Exception e) {
            // Handle parsing exceptions (e.g., invalid format)
            System.out.println("Invalid date format. Please use the format yyyy-MM-dd.");
            return null;
        }
    }

    private static boolean isFutureDate(LocalDate date) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Compare the entered date with the current date
        return date.isAfter(currentDate);
    }





}