package com.mercadolibre.desafioquality.DAO.FlightDAO;

import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;

import java.util.List;

public interface FlightSeatsDAO {

    public List<FlightSeatsDTO> getAllFlightSeats();
}
