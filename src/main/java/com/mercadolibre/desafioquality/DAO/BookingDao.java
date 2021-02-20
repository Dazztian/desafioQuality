package com.mercadolibre.desafioquality.DAO;

import com.mercadolibre.desafioquality.DTO.HotelDTO;

import java.util.List;

public interface BookingDao {

    public List<HotelDTO> getAllHotels();
}
