package com.mercadolibre.desafioquality.DTO;

import javax.validation.Valid;

public class BookHotelRoomRequestDTO {

    private String username;
    @Valid
    private BookingDTO bookingDTO;
    private PaymentMethodDTO paymentMethodDTO;

    public BookHotelRoomRequestDTO(String username, BookingDTO bookingDTO, PaymentMethodDTO paymentMethodDTO) {
        this.username = username;
        this.bookingDTO = bookingDTO;
        this.paymentMethodDTO = paymentMethodDTO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BookingDTO getBookingDTO() {
        return bookingDTO;
    }

    public void setBookingDTO(BookingDTO bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

    public PaymentMethodDTO getPaymentMethodDTO() {
        return paymentMethodDTO;
    }

    public void setPaymentMethodDTO(PaymentMethodDTO paymentMethodDTO) {
        this.paymentMethodDTO = paymentMethodDTO;
    }
}
