package com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs;

import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;

public class BookHotelRoomResponseDTO {


    private String userName;
    private Double amount;
    private Double interest;
    private Double total;
    private BookingDTO bookingDTO;
    private StatusCodeDTO statusCodeDTO;

    public BookHotelRoomResponseDTO(String userName, Double amount, Double interest, Double total, BookingDTO bookingDTO, StatusCodeDTO statusCodeDTO) {
        this.userName = userName;
        this.amount = amount;
        this.interest = interest;
        this.total = total;
        this.bookingDTO = bookingDTO;
        this.statusCodeDTO = statusCodeDTO;
    }

    public StatusCodeDTO getStatusCodeDTO() {
        return statusCodeDTO;
    }

    public void setStatusCodeDTO(StatusCodeDTO statusCodeDTO) {
        this.statusCodeDTO = statusCodeDTO;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public BookingDTO getBookingDTO() {
        return bookingDTO;
    }

    public void wsetBookingDTO(BookingDTO bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

}
