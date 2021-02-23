package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DAO.HotelRoomDAO.Impl.BookingDaoImpl;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.HotelDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.RequestDTO;

import java.util.List;
import java.util.Locale;

public class AvailabilityRequestValidation {



    public static boolean isValidDate(RequestDTO request)
    {
        if(request.getDateFrom()!= null && request.getDateTo()!= null)
            //la fecha de inicio de la request debe ser  ANTERIOR a su fecha de fin
            return request.getDateFrom().before(request.getDateTo() );

        return true;
    }

    public static boolean isValidDestination(RequestDTO request)
    {
        //Si el destino está presente, entonces nos fijamos si existe en la BD
        if (request.getDestination()!= null)
        {
            BookingDaoImpl apiBusqueda = new BookingDaoImpl();
            List<HotelDTO> matches = apiBusqueda.getAllHotels();
            return matches.stream().anyMatch( hotel -> hotel.getDestination().equalsIgnoreCase(request.getDestination().toLowerCase(Locale.ROOT)));
        }

        //Como no le pasamos ningun destino, entonces devolvemos true para que considere como válido el destino vacío
        return true;
    }

    public static boolean isValidButEmpty(RequestDTO request)
    {
        return request.getDestination()== null && request.getDateFrom()== null && request.getDateTo()== null;

    }


    public static ResponseDTO validateRequest(RequestDTO request)
    {
        if(isValidButEmpty(request))
            return new ResponseDTO("200", "request sin parametros, mostrando todos los destinos disponibles", null);

        if (!isValidDestination(request) )
            return new ResponseDTO("404", "Destino no encontrado", null);

        if(!isValidDate(request))
            return new ResponseDTO(
                    "404",
                    "La fecha de salida debe ser mayor a la de entrada",
                    null);


        //En el caso de que la request sea válida, entonces devolvemos este mensaje
        return new ResponseDTO("200", "Se han ingresado datos validos", null);
    }

}
