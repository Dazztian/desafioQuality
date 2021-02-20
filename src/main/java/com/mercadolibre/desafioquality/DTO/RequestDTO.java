package com.mercadolibre.desafioquality.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafioquality.utils.DateFormatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public class RequestDTO {

    //Me está levantando mal los datos!!!
    //Le ponga o no la anotation me levanta mal los datos

    //Me levanta los datos con el formato mm/dd/yyyy
    //Esto tengo que hacerle un refactor para que levante de la forma dd/mm/yyyy

    private Optional<Date> dateFrom;
    private Optional<Date> dateTo;
    private Optional<String> destination;

    public RequestDTO(Optional<Date> dateFrom, Optional<Date> dateTo, Optional<String> destination) throws ParseException {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        //this.dateFrom = DateFormatter.dateFormatter(dateFrom);
        //this.dateTo = DateFormatter.dateFormatter(dateTo);
        this.destination = destination;
    }

    public Optional<Date> getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Optional<Date> dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Optional<Date> getDateTo() {
        return dateTo;
    }

    public void setDateTo(Optional<Date> dateTo) {
        this.dateTo = dateTo;
    }

    public Optional<String> getDestination() {
        return destination;
    }

    public void setDestination(Optional<String> destination) {
        this.destination = destination;
    }
}
