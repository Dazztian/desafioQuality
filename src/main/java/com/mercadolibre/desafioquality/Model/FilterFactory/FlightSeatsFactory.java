package com.mercadolibre.desafioquality.Model.FilterFactory;

import com.mercadolibre.desafioquality.DTO.FlightDtos.FlightSeatRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightDtos.FlightSeatsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;


public class FlightSeatsFactory {

    public static Predicate<FlightSeatsDTO> getFlightSeatsFilter(FlightSeatRequestDTO request)
    {
        List<Predicate<FlightSeatsDTO>> allPredicates;
        allPredicates= getAllPredicates(request);
        return allPredicates.stream().reduce( p -> true, Predicate::and);
    }

    private static List<Predicate<FlightSeatsDTO>> getAllPredicates(FlightSeatRequestDTO request)
    {
        List<Predicate<FlightSeatsDTO>> allPredicates = new ArrayList<>();
        

        if (request.getDateFrom()!= null && request.getDateTo()== null) {
            allPredicates.add(p -> p.getDateFrom().compareTo(request.getDateFrom()) <= 0
                    && p.getDateTo().compareTo(request.getDateFrom()) >=0);
        }

        if (request.getDateTo()!= null && request.getDateFrom()== null) {
            allPredicates.add( p -> p.getDateTo().compareTo(request.getDateTo()) >= 0
                    && p.getDateFrom().compareTo(request.getDateTo()) <=0);

        }

        if (request.getDateFrom()!= null && request.getDateTo()!= null)
        {

            if(request.getDateFrom().before(request.getDateTo() ) )
            {
                allPredicates.add( p -> p.getDateFrom().before(request.getDateFrom())
                        && p.getDateTo().compareTo(request.getDateFrom()) >=0);
                allPredicates.add( p -> p.getDateTo().after(request.getDateTo())
                        && p.getDateFrom().compareTo(request.getDateTo()) <=0);

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

