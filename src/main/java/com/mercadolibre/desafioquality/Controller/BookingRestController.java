package com.mercadolibre.desafioquality.Controller;


import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.RequestDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsResponseDTO;
import com.mercadolibre.desafioquality.Service.impl.BookingServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class BookingRestController {


    @Autowired
    BookingServiceImpl bookingService;

    @PostMapping("/booking")
    public BookHotelRoomResponseDTO BookHotelRoom( @RequestBody BookHotelRoomRequestDTO request)
    {
        return bookingService.bookHotelRoom(request);
    }

    @GetMapping("/hotels")
    public ResponseDTO getAllAvailableHotelRooms(RequestDTO request)
    {
        return bookingService.getAllAvailableHotels(request);
    }

    @GetMapping("/flights")
    public FlightSeatsResponseDTO getAllAvailableFlights(FlightSeatsRequestDTO request)
    {
        return bookingService.getAllAvailableFlightSeats(request);
    }

    @PostMapping("/flight-reservation")
    public BookFlightSeatResponseDTO BookFlightSeat(@RequestBody BookFlightSeatsRequestDTO request)
    {
        return bookingService.BookFlightSeat(request);
    }

}