package com.mercadolibre.desafioquality.Service.FlightSeatsService;

import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsResponseDTO;

import java.util.List;

public interface FlightSeatService {



    public FlightSeatsResponseDTO getAllAvailableFlightSeats(FlightSeatsRequestDTO request);
    public List<FlightSeatsDTO> getFlightSeatsFiltered(FlightSeatsRequestDTO request);

    public BookFlightSeatResponseDTO BookFlightSeat(BookFlightSeatsRequestDTO request);
    public List<FlightSeatsDTO> getFlightSeatsFiltered(BookFlightSeatsRequestDTO request);

}
