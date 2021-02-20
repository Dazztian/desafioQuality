package com.mercadolibre.desafioquality.Service;

import com.mercadolibre.desafioquality.DTO.*;

import java.util.List;

public interface BookingService {


    public ResponseDTO getAllAvailableHotels(RequestDTO request);
    public List<HotelDTO> getHotelsFiltered(RequestDTO request);

    public BookHotelRoomResponseDTO bookHotel(BookHotelRoomRequestDTO bookHotelRoomRequestDTO);



}
