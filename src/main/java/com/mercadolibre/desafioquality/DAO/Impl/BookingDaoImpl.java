package com.mercadolibre.desafioquality.DAO.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafioquality.DAO.BookingDao;
import com.mercadolibre.desafioquality.DTO.AvailabilityDTOs.HotelDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingDaoImpl implements BookingDao {

    private List<HotelDTO> database;

    public BookingDaoImpl()  {
        database = this.loadDatabase();
    }

    @Override
    public List<HotelDTO> getAllHotels()
    {
        return new ArrayList<>(this.database);
    }


    public List<HotelDTO>  loadDatabase()  {
        File file= null;
        try {
            file = ResourceUtils.getFile("src/main/resources/hotels.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<HotelDTO>> typeRef = new TypeReference<>() {};
        List<HotelDTO> HotelDTOS = null;
        try {
            HotelDTOS = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HotelDTOS;
    }

}
