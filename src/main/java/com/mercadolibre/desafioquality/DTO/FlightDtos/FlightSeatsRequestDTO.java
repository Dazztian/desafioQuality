package com.mercadolibre.desafioquality.DTO.FlightDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatsRequestDTO {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd/MM/yyyy")
    private Date dateTo;
    private String origin;
    private String destination;
}
