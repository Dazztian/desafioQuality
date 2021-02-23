package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DAO.FlightDAO.Impl.FlightSeatsDAOImpl;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.StatusCodeDTO;
import com.mercadolibre.desafioquality.DTO.FlightDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.FlightDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightDtos.FlightSeatsResponseDTO;

import java.util.List;
import java.util.Locale;


public class FlightSeatsValidation {


    static FlightSeatsDAOImpl apiBusqueda = new FlightSeatsDAOImpl();
    static List<FlightSeatsDTO> matches = apiBusqueda.getAllFlightSeats();


    public static boolean isValidDate(FlightSeatsRequestDTO request)
    {
        if(request.getDateFrom()!= null && request.getDateTo()!= null)
            return request.getDateFrom().before(request.getDateTo() );

        return true;
    }

    public static boolean isValidDestination(FlightSeatsRequestDTO request)
    {
        if (request.getDestination()!= null)
        {
            return matches.stream().anyMatch( hotel -> hotel.getDestination().equalsIgnoreCase(request.getDestination().toLowerCase(Locale.ROOT)));
        }

        //Como no le pasamos ningun destino, entonces devolvemos true para que considere como válido el destino vacío
        return true;
    }

    public static boolean isValidOrigin(FlightSeatsRequestDTO request)
    {
        if (request.getOrigin()!= null)
        {
            return matches.stream().anyMatch( hotel -> hotel.getOrigin().equalsIgnoreCase(request.getOrigin().toLowerCase(Locale.ROOT)));
        }

        return true;
    }

    public static boolean isValidButEmpty(FlightSeatsRequestDTO request)
    {
        return  request.getDestination()== null  && request.getOrigin() == null &&
                request.getDateFrom()== null && request.getDateTo()== null;

    }


    public static FlightSeatsResponseDTO validateRequest(FlightSeatsRequestDTO request)
    {
        if(isValidButEmpty(request))
            return new FlightSeatsResponseDTO(new StatusCodeDTO("200", "request sin parametros, mostrando todos los asientos de avión disponibles"), null);

        if (!isValidDestination(request) )
            return new FlightSeatsResponseDTO(new StatusCodeDTO("404", "Destino no encontrado"), null);

        if (!isValidOrigin(request) )
            return new FlightSeatsResponseDTO(new StatusCodeDTO("404", "Origen no encontrado"), null);

        if(!isValidDate(request))
            return new FlightSeatsResponseDTO(new StatusCodeDTO("404", "La fecha de salida debe ser mayor a la de entrada"),
                    null);


        //En el caso de que la request sea válida, entonces devolvemos este mensaje
        return new FlightSeatsResponseDTO(new StatusCodeDTO("200", "Se han ingresado datos validos"), null);
    }

}
