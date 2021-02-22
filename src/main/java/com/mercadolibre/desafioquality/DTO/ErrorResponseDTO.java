package com.mercadolibre.desafioquality.DTO;

public class ErrorResponseDTO extends BookHotelRoomResponseDTO{

    public ErrorResponseDTO(String userName,  BookingDTO bookingDTO, StatusCodeDTO statusCodeDTO) {
        super(userName, 0.0, 0.0, 0.0, bookingDTO, statusCodeDTO);
    }
}
