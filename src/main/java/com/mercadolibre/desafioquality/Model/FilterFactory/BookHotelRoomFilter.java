package com.mercadolibre.desafioquality.Model.FilterFactory;

import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.HotelDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class BookHotelRoomFilter {

    public static Predicate<HotelDTO> getHotelRoomFilter(BookHotelRoomRequestDTO request)
    {
        List<Predicate<HotelDTO>> allPredicates;
        allPredicates= getAllPredicates(request);
        return allPredicates.stream().reduce( p -> true, Predicate::and);
    }

    private static List<Predicate<HotelDTO>> getAllPredicates(BookHotelRoomRequestDTO request)
    {
        List<Predicate<HotelDTO>> allPredicates = new ArrayList<>();


        //Solo deberíamos analizar el caso que ingresen fecha de salida y entrada
        if (request.getBooking().getDateTo()!= null && request.getBooking().getDateFrom()!= null)
        {
            if(request.getBooking().getDateFrom().before(request.getBooking().getDateTo() ) )
            {
                allPredicates.add( p -> p.getDisponibilityFrom().compareTo(request.getBooking().getDateFrom()) <= 0);
                allPredicates.add( p -> p.getDisponibilityUntil().compareTo(request.getBooking().getDateTo()) >= 0);
            }
        }


        if(request.getBooking().getHotelCode()!= null ) {
            allPredicates.add( p -> p.getCode().equalsIgnoreCase(request.getBooking().getHotelCode().toLowerCase(Locale.ROOT)));
        }

        if(request.getBooking().getRoomType()!= null ) {
            allPredicates.add( p -> p.getRoomType().equalsIgnoreCase(request.getBooking().getRoomType().toLowerCase(Locale.ROOT)));
        }

        if(request.getBooking().getDestination()!= null ) {
            allPredicates.add( p -> p.getDestination().equalsIgnoreCase(request.getBooking().getDestination().toLowerCase(Locale.ROOT)));
        }


        //Siempre checkeamos que no esté ocupada
        allPredicates.add( p -> p.getOcuppied() == false );

        return allPredicates;
    }
}

