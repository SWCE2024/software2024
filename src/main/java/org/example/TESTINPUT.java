package org.example;

import static java.lang.Integer.parseInt;

public class TESTINPUT {
    private TESTINPUT(){

    }


    public static boolean emptyTest(String name) {
        if (name.equals(null)||name.isEmpty())
            return false;
        else return true;
    }



    public static boolean VenueNameTest(String name) {
      if (name.equals(null)||name.isEmpty())
          return false;
      else return true;
    }
    public static boolean VenueLocationTest(String Location) {
        if (Location.equals(null)||Location.isEmpty())
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





}