package com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.PeopleDTO;
import com.mercadolibre.desafioquality.DTO.PaymentMethodDTO;
import com.mercadolibre.desafioquality.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*/
public class FlightReservationDTO {

    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private Integer seats;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateTo;
    private List<PeopleDTO> people;
    private PaymentMethodDTO paymentMethod;

    public FlightReservationDTO(String flightNumber, String origin, String destination, String seatType, Integer seats, String dateFrom, String dateTo, List<PeopleDTO> people, PaymentMethodDTO paymentMethod) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.seatType = seatType;
        this.seats = seats;
        this.dateFrom = new Date(DateUtils.dateFormatConvertor(dateFrom));
        this.dateTo = new Date(DateUtils.dateFormatConvertor(dateTo));
        this.people = people;
        this.paymentMethod = paymentMethod;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = new Date(DateUtils.dateFormatConvertor(dateFrom));
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = new Date(DateUtils.dateFormatConvertor(dateTo));
    }

    public List<PeopleDTO> getPeople() {
        return people;
    }

    public void setPeople(List<PeopleDTO> people) {
        this.people = people;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
