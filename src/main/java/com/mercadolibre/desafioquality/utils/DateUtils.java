package com.mercadolibre.desafioquality.utils;

import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;

import java.util.Date;

public class DateUtils {

    public static Long getDaysDifference(BookHotelRoomResponseDTO responseDTO) {
        Date date1 = responseDTO.getBookingDTO().getDateFrom();
        Date date2 = responseDTO.getBookingDTO().getDateTo();

        long diffTime = date2.getTime() - date1.getTime();
        long diffDays = diffTime / (1000 * 60 * 60 * 24);

        return diffDays;

    }
}
