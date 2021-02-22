package com.mercadolibre.desafioquality.Controller;


import com.mercadolibre.desafioquality.DTO.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomResponseDTO;
import com.mercadolibre.desafioquality.DTO.RequestDTO;
import com.mercadolibre.desafioquality.DTO.ResponseDTO;
import com.mercadolibre.desafioquality.Service.impl.BookingServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingRestController {


    @Autowired
    BookingServiceImpl bookingService;

    @GetMapping("/booking")
    public BookHotelRoomResponseDTO BookHotelRoom(@RequestBody BookHotelRoomRequestDTO request)
    {
        BookHotelRoomRequestDTO unarequest =request;
        return bookingService.bookHotel(request);
    }

    @GetMapping("/hotels")
    public ResponseDTO getAllAvailableHotelRooms(RequestDTO request)
    {
        return bookingService.getAllAvailableHotels(request);
    }

}