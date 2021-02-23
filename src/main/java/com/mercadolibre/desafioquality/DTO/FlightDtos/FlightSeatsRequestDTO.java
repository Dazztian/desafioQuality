package com.mercadolibre.desafioquality.DTO.FlightDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafioquality.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FlightSeatsRequestDTO {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String origin;
    private String destination;


    //El tipo Date siempre me levanta los datos del tipo mm/dd/yyyy
    //Yo puedo mandar la fecha en formato "criollo" y luego convertirla a lo que espera
    public FlightSeatsRequestDTO(String dateFrom, String dateTo, String origin, String destination)  {
        this.dateFrom = new Date(DateUtils.dateFormatConvertor(dateFrom));
        this.dateTo = new Date(DateUtils.dateFormatConvertor(dateTo));
        this.origin = origin;
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
}
