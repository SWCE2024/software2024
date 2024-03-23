package org.example;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReminderForUser
{
    public static void check()
    {

        LocalDate currentdate =LocalDate.now();

        List<Date> date=new ArrayList<>();
        date =Database.getDateEvents();



             for (int i =0 ;i<date.size();i++)
             {

                 if (currentdate.toString().equals(date.get(i).toString()))
                 {



                 }

             }


             System.out.println(currentdate);



        System.out.println(currentdate);
        System.out.println(date);




    }

}
