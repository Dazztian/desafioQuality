package com.mercadolibre.desafioquality.DTO.FlightDtos;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatsResponseDTO {

    List<FlightSeatsDTO> flightSeatsDTOSList;
}
