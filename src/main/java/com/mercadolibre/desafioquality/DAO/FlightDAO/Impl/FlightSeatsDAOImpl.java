package com.mercadolibre.desafioquality.DAO.FlightDAO.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafioquality.DAO.FlightDAO.FlightSeatsDAO;
import com.mercadolibre.desafioquality.DTO.FlightDtos.FlightSeatsDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightSeatsDAOImpl implements FlightSeatsDAO {

    private List<FlightSeatsDTO> database;

    public FlightSeatsDAOImpl()  {
        database = this.loadDatabase();
    }

    @Override
    public List<FlightSeatsDTO> getAllFlightSeats() {
        return new ArrayList<>(this.database);
    }


    public List<FlightSeatsDTO>  loadDatabase()  {
        File file= null;
        try {
            file = ResourceUtils.getFile("src/main/resources/flightsSeats.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<FlightSeatsDTO>> typeRef = new TypeReference<>() {};
        List<FlightSeatsDTO> flightSeatsDTOS = null;
        try {
            flightSeatsDTOS = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flightSeatsDTOS;
    }

    
}
