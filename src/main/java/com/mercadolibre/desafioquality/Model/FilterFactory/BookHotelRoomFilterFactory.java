package com.mercadolibre.desafioquality.Model.FilterFactory;

import com.mercadolibre.desafioquality.DTO.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.HotelDTO;
import com.mercadolibre.desafioquality.DTO.RequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

//Esto """"""PODRÍA intentar""""" resolver con generics, pero debido al scope de tiempo prefiero no hacerlo así
//SIN EMBARGO el comportamiento de getAllPredicates cambia según el tipo de dato, ya que no es lo mismo
//el tratamiento que se le da un optional a un dato que no es optional

public class BookHotelRoomFilterFactory {

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
        /*if (request.getBookingDTO().getDateTo()!= null && request.getBookingDTO().getDateFrom()!= null)
        {
            if(request.getBookingDTO().getDateFrom().before(request.getBookingDTO().getDateTo() ) )
            {
                allPredicates.add( p -> p.getDisponibilityFrom().before(request.getBookingDTO().getDateFrom())
                                     && p.getDisponibilityUntil().compareTo(request.getBookingDTO().getDateFrom()) >=0);
                allPredicates.add( p -> p.getDisponibilityUntil().after(request.getBookingDTO().getDateTo())
                                     && p.getDisponibilityFrom().compareTo(request.getBookingDTO().getDateTo()) <=0);

            }
        }
        */

        if(request.getBookingDTO().getHotelCode()!= null ) {
            allPredicates.add( p -> p.getCode().equalsIgnoreCase(request.getBookingDTO().getHotelCode().toLowerCase(Locale.ROOT)));
        }

        if(request.getBookingDTO().getRoomType()!= null ) {
            allPredicates.add( p -> p.getRoomType().equalsIgnoreCase(request.getBookingDTO().getRoomType().toLowerCase(Locale.ROOT)));
        }

        if(request.getBookingDTO().getDestination()!= null ) {
            allPredicates.add( p -> p.getDestination().equalsIgnoreCase(request.getBookingDTO().getDestination().toLowerCase(Locale.ROOT)));
        }


        //Debería agregar un filter que compare el peopleAmount con la cantidad de people que mando en la lista


        //Siempre checkeamos que no esté ocupada
        allPredicates.add( p -> p.getOcuppied() == false );

        return allPredicates;
    }
}

