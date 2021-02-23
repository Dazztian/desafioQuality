package com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO;

import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;

public class ErrorResponseDTO extends BookFlightSeatResponseDTO {

    public ErrorResponseDTO(String userName, FlightReservationDTO flightReservationDTO, StatusCodeDTO statusCodeDTO) {
        super(userName, 0.0, 0.0, 0.0, flightReservationDTO, statusCodeDTO);
    }
}

