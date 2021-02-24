package com.mercadolibre.desafioquality.Model.FilterFactory;

import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;


public class FlightSeatsFilter {

    public static Predicate<FlightSeatsDTO> getFlightSeatsFilter(FlightSeatsRequestDTO request)
    {
        List<Predicate<FlightSeatsDTO>> allPredicates;
        allPredicates= getAllPredicates(request);
        return allPredicates.stream().reduce( p -> true, Predicate::and);
    }

    private static List<Predicate<FlightSeatsDTO>> getAllPredicates(FlightSeatsRequestDTO request)
    {
        List<Predicate<FlightSeatsDTO>> allPredicates = new ArrayList<>();
        

        if (request.getDateFrom()!= null && request.getDateTo()== null) {
            allPredicates.add(p -> p.getDateFrom().compareTo(request.getDateFrom()) == 0); }

        if (request.getDateTo()!= null && request.getDateFrom()== null) {
            allPredicates.add( p -> p.getDateTo().compareTo(request.getDateTo()) == 0);
        }

        if (request.getDateFrom()!= null && request.getDateTo()!= null)
        {

            if(request.getDateFrom().before(request.getDateTo() ) )
            {
                allPredicates.add( p -> p.getDateFrom().compareTo(request.getDateFrom()) ==0);
                allPredicates.add( p -> p.getDateTo().compareTo(request.getDateTo()) == 0);

            }
        }


        if(request.getOrigin()!= null ) {
            allPredicates.add( p -> p.getOrigin().equalsIgnoreCase(request.getOrigin().toLowerCase(Locale.ROOT)));
        }

        if(request.getDestination()!= null ) {
            allPredicates.add( p -> p.getDestination().equalsIgnoreCase(request.getDestination().toLowerCase(Locale.ROOT)));
        }


        return allPredicates;
    }
}

