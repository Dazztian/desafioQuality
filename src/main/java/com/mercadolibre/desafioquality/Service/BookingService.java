package com.mercadolibre.desafioquality.Service;

import com.mercadolibre.desafioquality.DTO.*;

import java.text.ParseException;
import java.util.List;

public interface BookingService {


    public ResponseDTO getAllAvailableHotels(RequestDTO request);
    public List<HotelDTO> getHotelsFiltered(RequestDTO request);
    public List<HotelDTO> getHotelRoomsFiltered(BookHotelRoomRequestDTO bookHotelRoomRequestDTO);

    public BookHotelRoomResponseDTO bookHotel(BookHotelRoomRequestDTO bookHotelRoomRequestDTO) throws ParseException;



}
