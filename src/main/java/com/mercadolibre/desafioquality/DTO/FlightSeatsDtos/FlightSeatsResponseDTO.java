package com.mercadolibre.desafioquality.DTO.FlightSeatsDtos;

import java.util.List;


import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatsResponseDTO {


    private StatusCodeDTO statusCode;
    List<FlightSeatsDTO> flightSeatsDTOSList;
}
