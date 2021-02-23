package com.mercadolibre.desafioquality.Model.FilterFactory;

import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;


public class BookFlightSeatFilterFactory {

    public static Predicate<FlightSeatsDTO> getFlightSeatsFilter(BookFlightSeatsRequestDTO request)
    {
        List<Predicate<FlightSeatsDTO>> allPredicates;
        allPredicates= getAllPredicates(request);
        return allPredicates.stream().reduce( p -> true, Predicate::and);
    }

    private static List<Predicate<FlightSeatsDTO>> getAllPredicates(BookFlightSeatsRequestDTO request)
    {
        List<Predicate<FlightSeatsDTO>> allPredicates = new ArrayList<>();


        //ME ESTA PINCHANDO LAS FECHAS
        /*if (request.getFlightReservation().getDateFrom()!= null && request.getFlightReservation().getDateTo()!= null)
        {

            if(request.getFlightReservation().getDateFrom().before(request.getFlightReservation().getDateTo() ) )
            {
                allPredicates.add( p -> p.getDateFrom().compareTo(request.getFlightReservation().getDateFrom()) ==0);
                allPredicates.add( p -> p.getDateTo().compareTo(request.getFlightReservation().getDateTo()) == 0);

            }
        }*/

        if(request.getFlightReservation().getFlightNumber()!= null ) {
            allPredicates.add( p -> p.getFlightNumber().equalsIgnoreCase(request.getFlightReservation().getFlightNumber().toLowerCase(Locale.ROOT)));
        }

        if(request.getFlightReservation().getSeatType()!= null ) {
            allPredicates.add( p -> p.getSeatType().equalsIgnoreCase(request.getFlightReservation().getSeatType().toLowerCase(Locale.ROOT)));
        }

        if(request.getFlightReservation().getOrigin()!= null ) {
            allPredicates.add( p -> p.getOrigin().equalsIgnoreCase(request.getFlightReservation().getOrigin().toLowerCase(Locale.ROOT)));
        }

        if(request.getFlightReservation().getDestination()!= null ) {
            allPredicates.add( p -> p.getDestination().equalsIgnoreCase(request.getFlightReservation().getDestination().toLowerCase(Locale.ROOT)));
        }


        return allPredicates;
    }
}

