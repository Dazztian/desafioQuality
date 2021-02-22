package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DAO.Impl.BookingDaoImpl;
import com.mercadolibre.desafioquality.DTO.*;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class BookRequestValidation {

    

    public static boolean isValidDate(BookHotelRoomRequestDTO request)
    {
        if(request.getBookingDTO().getDateFrom()!= null && request.getBookingDTO().getDateTo()!= null)
            return request.getBookingDTO().getDateFrom().before(request.getBookingDTO().getDateTo() );

        return true;
    }

    public static boolean isValidDestination(BookHotelRoomRequestDTO request)
    {
        if (request.getBookingDTO().getDestination()!= null)
        {
            BookingDaoImpl apiBusqueda = new BookingDaoImpl();
            List<HotelDTO> matches = apiBusqueda.getAllHotels();
            return matches.stream().anyMatch( hotel -> hotel.getDestination().equalsIgnoreCase(request.getBookingDTO().getDestination().toLowerCase(Locale.ROOT)));
        }

        return true;
    }



    public static BookHotelRoomResponseDTO validateRequest(BookHotelRoomRequestDTO request)
    {

        if (!isValidDestination(request) )
            return new BookHotelRoomResponseDTO(request.getUsername(), 0.0, 0.0,0.0,
                            request.getBookingDTO(), new StatusCodeDTO("404","Destino no encontrado"));

        if(!isValidDate(request))
            return new BookHotelRoomResponseDTO(request.getUsername(), 0.0, 0.0,0.0,
                    request.getBookingDTO(),
                    new StatusCodeDTO("404", "La fecha de salida debe ser mayor a la de entrada"));


        //En el caso de que la request sea válida, entonces devolvemos este mensaje
        return new BookHotelRoomResponseDTO(request.getUsername(), 0.0, 0.0,0.0,
                request.getBookingDTO(), new StatusCodeDTO("200", "el proceso termino satisfactoriamente"));
    }

}
