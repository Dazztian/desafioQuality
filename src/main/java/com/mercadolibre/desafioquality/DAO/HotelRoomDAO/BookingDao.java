package com.mercadolibre.desafioquality.DAO.HotelRoomDAO;

import com.mercadolibre.desafioquality.DTO.AvailabilityDTOs.HotelDTO;

import java.util.List;

public interface BookingDao {

    public List<HotelDTO> getAllHotels();
}
