package com.mercadolibre.desafioquality.Service.HotelRoomService;

import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.HotelDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.RequestDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;

import java.util.List;

public interface HotelRoomService {


    public ResponseDTO getAllAvailableHotels(RequestDTO request);
    public List<HotelDTO> getHotelsFiltered(RequestDTO request);

    public List<HotelDTO> getHotelRoomsFiltered(BookHotelRoomRequestDTO bookHotelRoomRequestDTO);
    public BookHotelRoomResponseDTO bookHotelRoom(BookHotelRoomRequestDTO bookHotelRoomRequestDTO);
}
