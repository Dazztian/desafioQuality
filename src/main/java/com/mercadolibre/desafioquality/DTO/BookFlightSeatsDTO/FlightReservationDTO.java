package com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.PeopleDTO;
import com.mercadolibre.desafioquality.DTO.PaymentMethodDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightReservationDTO {

    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private Integer seats;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateTo;
    private List<PeopleDTO> people;
    private PaymentMethodDTO paymentMethod;


}
