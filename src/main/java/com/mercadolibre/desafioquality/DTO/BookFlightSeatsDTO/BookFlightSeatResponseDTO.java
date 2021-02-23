package com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO;

import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookFlightSeatResponseDTO {

    private String userName;
    private Double amount;
    private Double interest;
    private Double total;
    private FlightReservationDTO flightReservation;
    private StatusCodeDTO statusCode;



}
