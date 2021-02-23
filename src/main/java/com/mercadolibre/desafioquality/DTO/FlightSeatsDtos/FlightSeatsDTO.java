package com.mercadolibre.desafioquality.DTO.FlightSeatsDtos;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafioquality.utils.DateUtils;

public class FlightSeatsDTO {

    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private Integer price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateTo;

    public FlightSeatsDTO() {
    }

    public FlightSeatsDTO(String flightNumber, String origin, String destination, String seatType, Integer price, String dateFrom, String dateTo) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.seatType = seatType;
        this.price = price;
        this.dateFrom = new Date(DateUtils.dateFormatConvertor(dateFrom));
        this.dateTo = new Date(DateUtils.dateFormatConvertor(dateTo));
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = new Date(DateUtils.dateFormatConvertor(dateFrom));;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = new Date(DateUtils.dateFormatConvertor(dateTo));;
    }
}
