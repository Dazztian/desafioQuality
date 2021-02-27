package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsResponseDTO;


public class FlightSeatsValidation extends  Validator{



    public static boolean isValidDate(FlightSeatsRequestDTO request)
    {
       return  Validator.isValidDate(request.getDateFrom(), request.getDateTo());
    }

    public static boolean isValidDestination(FlightSeatsRequestDTO request)
    {
        return Validator.isValidDestination(request.getDestination());
    }

    public static boolean isValidOrigin(FlightSeatsRequestDTO request)
    {
        return Validator.isValidOrigin(request.getOrigin());
    }

    public static boolean isValidButEmpty(FlightSeatsRequestDTO request)
    {

        return Validator.isValidButEmpty(
                request.getDestination(), request.getOrigin(),
                request.getDateFrom(), request.getDateTo() );

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
