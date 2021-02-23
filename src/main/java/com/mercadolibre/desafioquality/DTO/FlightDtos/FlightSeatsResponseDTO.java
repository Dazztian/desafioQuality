package com.mercadolibre.desafioquality.DTO.FlightDtos;

import java.util.List;


import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.StatusCodeDTO;
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
