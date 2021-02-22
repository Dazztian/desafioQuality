package com.mercadolibre.desafioquality.DTO.AvailabilityDTOs;

import java.util.List;

public class ResponseDTO {

    private String statusCode;
    private String message;
    private List<HotelDTO> hotelList;

    public ResponseDTO(String statusCode, String message, List<HotelDTO> hotelList) {
        this.statusCode = statusCode;
        this.message = message;
        this.hotelList = hotelList;
    }

    public List<HotelDTO> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<HotelDTO> hotelList) {
        this.hotelList = hotelList;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
