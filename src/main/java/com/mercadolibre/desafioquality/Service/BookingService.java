package com.mercadolibre.desafioquality.Service;

import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.HotelDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.RequestDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsResponseDTO;

import java.util.List;

public interface BookingService {


    public ResponseDTO getAllAvailableHotels(RequestDTO request);
    public List<HotelDTO> getHotelsFiltered(RequestDTO request);

    public List<HotelDTO> getHotelRoomsFiltered(BookHotelRoomRequestDTO bookHotelRoomRequestDTO);
    public BookHotelRoomResponseDTO bookHotelRoom(BookHotelRoomRequestDTO bookHotelRoomRequestDTO);


    public FlightSeatsResponseDTO getAllAvailableFlightSeats(FlightSeatsRequestDTO request);
    public List<FlightSeatsDTO> getFlightSeatsFiltered(FlightSeatsRequestDTO request);

    public BookFlightSeatResponseDTO BookFlightSeat(BookFlightSeatsRequestDTO request);
    public List<FlightSeatsDTO> getFlightSeatsFiltered(BookFlightSeatsRequestDTO request);


}
