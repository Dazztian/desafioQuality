package com.mercadolibre.desafioquality.Model;

import com.mercadolibre.desafioquality.DTO.HotelDTO;
import com.mercadolibre.desafioquality.DTO.RequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;


public class HotelFilterFactory {

    public static Predicate<HotelDTO> getHotelFilter(RequestDTO request)
    {
        List<Predicate<HotelDTO>> allPredicates;
        allPredicates= getAllPredicates(request);
        return allPredicates.stream().reduce( p -> true, Predicate::and);
    }

    private static List<Predicate<HotelDTO>> getAllPredicates(RequestDTO request)
    {
        List<Predicate<HotelDTO>> allPredicates = new ArrayList<>();



        if (request.getDateFrom().isPresent() && request.getDateTo().isEmpty()) {
            //Comparo que tenga disponibilidad desde antes o la misma fecha que me piden
            allPredicates.add(p -> p.getDisponibilityFrom().compareTo(request.getDateFrom().get()) <= 0
            //Comparo que la fecha de inicio no sea POSTERIOR a la de maxima disponibilidad
                                && p.getDisponibilityUntil().compareTo(request.getDateFrom().get()) >=0);
        }

        if (request.getDateTo().isPresent() && request.getDateFrom().isEmpty()) {
            //Comparo que la fecha de fin disponible sea MAYOR O IGUAL a la que piden
            allPredicates.add( p -> p.getDisponibilityUntil().compareTo(request.getDateTo().get()) >= 0
            //Comparo que la fecha de FIN de la request sea POSTERIOR a la de inicio de disponibilidad
                    && p.getDisponibilityFrom().compareTo(request.getDateTo().get()) <=0);

        }

        if (request.getDateFrom().isPresent() && request.getDateTo().isPresent())
        {

            //COMPRUEBO si la fecha de ingreso es ANTERIOR a la de egreso
            if(request.getDateFrom().get().before(request.getDateTo().get() ) )
            {
                allPredicates.add( p -> p.getDisponibilityFrom().before(request.getDateFrom().get())
                                     && p.getDisponibilityUntil().compareTo(request.getDateFrom().get()) >=0);
                allPredicates.add( p -> p.getDisponibilityUntil().after(request.getDateTo().get())
                                        && p.getDisponibilityFrom().compareTo(request.getDateTo().get()) <=0);

            }
        }


        if(request.getDestination().isPresent() ) {
            allPredicates.add( p -> p.getDestination().equalsIgnoreCase(request.getDestination().get().toLowerCase(Locale.ROOT)));
        }

        //Siempre checkeamos que no esté ocupada
        allPredicates.add( p -> p.getOcuppied() == false );

        return allPredicates;
    }
}
