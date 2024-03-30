package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;

import static java.lang.Integer.parseInt;
import static org.example.SignUpController.logger;


public class TESTINPUT {
    private TESTINPUT(){

    }
    public static boolean emptyTest(String name) {
        return !(name == null || name.isEmpty());
    }

    public static boolean venueNameTest(String name) {
        return !(name == null || name.isEmpty());
    }
    public static boolean venueLocationTest(String location) {
        return !(location==null||location.isEmpty());
    }

    public static boolean venueCapacityTest(String capacity) {
        int i = parseInt(capacity);
        return !(i<=0);
    }

    public static boolean venuePricingTest(String pricing) {
        int i = parseInt(pricing);
        return !(i<=0);
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

    public static boolean dateTest(String date){
        LocalDate enteredDate = parseUserInput(date);

        if (enteredDate != null && isFutureDate(enteredDate)) {
            return true;
        } else {
            logger.log(Level.SEVERE,"Invalid date, the date should be in the future.");
            return false;
        }

    }

    public static boolean timeTest(String time){
        return (isValidTime(time)) ;

    }

    public static boolean countTest(String attendecount){
        try {
            int count = Integer.parseInt(attendecount);

            return (isValidCount(count));
        } catch (NumberFormatException e) {

            logger.log(Level.SEVERE,"Invalid input. Please enter a valid integer.");
            return false;
        }

    }
    public static boolean pictureTest(String picture){
        return (picture.indexOf(".png")!= -1 || picture.indexOf(".jpg")!= -1) ;
    }

    public static boolean checkInputs(String phoneNumber, String price){



        if (!(phoneNumber.length()==10)) {
            logger.log(Level.SEVERE,"Invalid phone number. Phone number should consist of 10 digits.");
            return false;
        }
        if (Integer.parseInt(price)<0) {
            logger.log(Level.SEVERE,"Invalid price. Price should be greater than zero.");
            return false;
        }
        return true;
    }
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(userInput, formatter);
        } catch (Exception e) {

            logger.log(Level.SEVERE,"Invalid date format. Please use the format yyyy-MM-dd.");
            return null;
        }
    }

    private static boolean isFutureDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return date.isAfter(currentDate);
    }
}