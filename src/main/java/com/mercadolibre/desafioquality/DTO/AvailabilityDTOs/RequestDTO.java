package com.mercadolibre.desafioquality.DTO.AvailabilityDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafioquality.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public class RequestDTO {


    //Me levanta los datos con el formato mm/dd/yyyy y necesito que estén con el formato dd/mm/yyyy
    //Puedo hacerle un refactor con lo que hay en la parte de FlightSeats
    //Mi sospecha es que se debe al tipo de dato optional que me está condicionando

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
