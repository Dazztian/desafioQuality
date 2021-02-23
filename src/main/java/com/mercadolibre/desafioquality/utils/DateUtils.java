package com.mercadolibre.desafioquality.utils;

import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static Long getDaysDifference(BookHotelRoomResponseDTO responseDTO) {
        Date date1 = responseDTO.getBookingDTO().getDateFrom();
        Date date2 = responseDTO.getBookingDTO().getDateTo();

        long diffTime = date2.getTime() - date1.getTime();
        long diffDays = diffTime / (1000 * 60 * 60 * 24);

        return diffDays;

    }

    //El String llega con la forma dd/mm/yyyy
    //el metodo la  reconvierte a mm/dd/yyyy
    public static String dateFormatConvertor(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String result =LocalDate.parse(date, formatter).format(formatter2);

        return result;

    }

}
