package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.RequestDTO;

public class AvailabilityRequestValidation {



    public static boolean isValidDate(RequestDTO  request)
    {
        return  Validator.isValidDate(request.getDateFrom(), request.getDateTo());
    }

    public static boolean isValidDestination(RequestDTO  request)
    {
        return Validator.isValidDestination(request.getDestination());
    }

    public static boolean isValidButEmpty(RequestDTO  request)
    {

        return Validator.isValidButEmpty(request.getDestination(), request.getDateFrom(), request.getDateTo() );

    }


    public static ResponseDTO validateRequest(RequestDTO request)
    {

        Boolean result = isValidButEmpty(request);
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
