package com.mercadolibre.desafioquality.Controller;


import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityDTOs.RequestDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.Service.impl.BookingServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BookingRestController {


    @Autowired
    BookingServiceImpl bookingService;

    @GetMapping("/booking")
    public BookHotelRoomResponseDTO BookHotelRoom(@Valid @RequestBody BookHotelRoomRequestDTO request, BindingResult result)
    {
        //if(result.hasErrors())

            return bookingService.bookHotel(request);

    }

    @GetMapping("/hotels")
    public ResponseDTO getAllAvailableHotelRooms(RequestDTO request)
    {
        return bookingService.getAllAvailableHotels(request);
    }

    @GetMapping("/flights")
    public ResponseDTO getAllAvailableFlights(RequestDTO request)
    {
        return bookingService.getAllAvailableHotels(request);
    }

}