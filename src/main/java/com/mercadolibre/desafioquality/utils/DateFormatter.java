package com.mercadolibre.desafioquality.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DateFormatter {


    public static Optional<Date> dateFormatter(Optional<Date> date) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        if (date.isPresent())
        {

            String formatedDate = formatter.format(date.get());

            Date newDate = formatter.parse(formatedDate);

            SimpleDateFormat postFormat = new SimpleDateFormat("MM/dd/yyyy");
            String formatedDate2 = postFormat.format(newDate);

            Date newDate2 = StringToDate(formatedDate2);

            return Optional.of(newDate2);

        }
        return Optional.empty();
    }

    public static Date StringToDate(String dob) throws ParseException {
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(dob);
        System.out.println("Date object value: "+date);
        return date;
    }


}
