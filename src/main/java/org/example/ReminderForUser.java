package org.example;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;

public class ReminderForUser
{
    private ReminderForUser()
    {
    }

    public static void check() throws SQLException {

        LocalDate currentdate =LocalDate.now();

        List<Date> date;
        List<String> cid =Database.custumerCID;
        String gmail ;

        date =Database.getDateEvents();

             for (int i =0 ;i<date.size();i++)
             {

                 if (currentdate.toString().equals(date.get(i).toString()))
                 {
                     gmail =Database.getgmailReminder(cid.get(i));
                     ReminderCustomer.sendReminder(gmail);

                 }

             }

    }

}
