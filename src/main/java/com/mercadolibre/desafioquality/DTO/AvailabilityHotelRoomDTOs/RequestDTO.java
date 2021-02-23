package com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafioquality.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class RequestDTO {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String destination;

    public RequestDTO() {
    }

    public RequestDTO(String dateFrom, String dateTo, String destination) throws ParseException {
        this.dateFrom = new Date(DateUtils.dateFormatConvertor(dateFrom));
        this.dateTo = new Date(DateUtils.dateFormatConvertor(dateTo));
        this.destination = destination;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
