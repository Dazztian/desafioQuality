package com.mercadolibre.desafioquality.DTO.FlightDtos;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatsDTO {

    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private String price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd/MM/yyyy")
    private Date dateTo;
}
