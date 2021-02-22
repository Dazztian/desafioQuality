package com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs;

import javax.validation.Valid;

public class BookHotelRoomRequestDTO {

    private String username;
    @Valid
    private BookingDTO booking;

    public BookHotelRoomRequestDTO(String username, BookingDTO booking) {
        this.username = username;
        this.booking = booking;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }
}
