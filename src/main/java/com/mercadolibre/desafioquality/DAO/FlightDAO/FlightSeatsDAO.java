package com.mercadolibre.desafioquality.DAO.FlightDAO;

import com.mercadolibre.desafioquality.DTO.FlightDtos.FlightSeatsDTO;

import java.util.List;

public interface FlightSeatsDAO {

    public List<FlightSeatsDTO> getAllFlightSeats();
}
