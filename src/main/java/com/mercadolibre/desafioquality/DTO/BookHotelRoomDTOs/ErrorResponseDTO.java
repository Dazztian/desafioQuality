package com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs;

import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;

public class ErrorResponseDTO extends BookHotelRoomResponseDTO {

    public ErrorResponseDTO(String userName, BookingDTO bookingDTO, StatusCodeDTO statusCodeDTO) {
        super(userName, 0.0, 0.0, 0.0, bookingDTO, statusCodeDTO);
    }
}
