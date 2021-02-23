package com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO;

import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookFlightSeatsRequestDTO {

    private String userName;
    private FlightReservationDTO flightReservation;


}
