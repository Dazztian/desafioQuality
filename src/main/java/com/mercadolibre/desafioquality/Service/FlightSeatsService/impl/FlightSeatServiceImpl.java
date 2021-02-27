package com.mercadolibre.desafioquality.Service.FlightSeatsService.impl;

import com.mercadolibre.desafioquality.DAO.FlightDAO.Impl.FlightSeatsDAOImpl;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsResponseDTO;
import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import com.mercadolibre.desafioquality.Model.FilterFactory.BookFlightSeatFilter;
import com.mercadolibre.desafioquality.Model.FilterFactory.FlightSeatsFilter;
import com.mercadolibre.desafioquality.Model.Validation.BookFlightSeatRequestValidation;
import com.mercadolibre.desafioquality.Model.Validation.FlightSeatsValidation;
import com.mercadolibre.desafioquality.Service.FlightSeatsService.FlightSeatService;
import com.mercadolibre.desafioquality.utils.InterestUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FlightSeatServiceImpl implements FlightSeatService {



    FlightSeatsDAOImpl apiBusquedaFlightSeats = new FlightSeatsDAOImpl();

    public FlightSeatServiceImpl() { }

    @Override
    public BookFlightSeatResponseDTO BookFlightSeat(BookFlightSeatsRequestDTO bookFlightSeatsRequestDTO)
    {

        //1ero valido los datos
        BookFlightSeatResponseDTO bookFlightSeatResponseDTO= BookFlightSeatRequestValidation.validateRequest(bookFlightSeatsRequestDTO);

        //Seteamos la response
        setResponseFromBookFlightSeat(bookFlightSeatsRequestDTO, bookFlightSeatResponseDTO);

        return bookFlightSeatResponseDTO;
    }

    @Override
    public FlightSeatsResponseDTO getAllAvailableFlightSeats(FlightSeatsRequestDTO request) {

        FlightSeatsResponseDTO responseDTO = FlightSeatsValidation.validateRequest(request);

        //Modifico la response según lo q haya devuelto la validación de la request
        setResponseFromFlightSeat(request, responseDTO);

        return responseDTO;

    }


    @Override
    public List<FlightSeatsDTO> getFlightSeatsFiltered(BookFlightSeatsRequestDTO request) {

        List<FlightSeatsDTO>  matches = apiBusquedaFlightSeats.getAllFlightSeats();
        Predicate<FlightSeatsDTO> compositeFilterRule;
        compositeFilterRule = BookFlightSeatFilter.getFlightSeatsFilter(request);


        return matches.stream()
                .filter(compositeFilterRule)
                .collect(Collectors.toList());
    }


    @Override
    public List<FlightSeatsDTO> getFlightSeatsFiltered(FlightSeatsRequestDTO request) {

        List<FlightSeatsDTO>  matches = apiBusquedaFlightSeats.getAllFlightSeats();
        Predicate<FlightSeatsDTO> compositeFilterRule;
        compositeFilterRule = FlightSeatsFilter.getFlightSeatsFilter(request);


        return matches.stream()
                .filter(compositeFilterRule)
                .collect(Collectors.toList());
    }


    private void setResponseFromFlightSeat(FlightSeatsRequestDTO request, FlightSeatsResponseDTO responseDTO) {

        List<FlightSeatsDTO> flightsFiltered;

        //Compruebo que no haya errores en la request de ningún tipo
        if (responseDTO.getStatusCode().getCode().equalsIgnoreCase("200"))
        {
            //Aplicamos los filtros
            flightsFiltered = getFlightSeatsFiltered(request);

            //Si a pesar de ser válida la request no encontró ningún destino disponible
            if (flightsFiltered.size() < 1)
            {
                responseDTO.setStatusCode( new StatusCodeDTO("200","No se encontro ningun asiento de avión disponible para las fechas indicadas"));
            }
            else //Si llegaste hasta acá es el camino feliz
            {
                responseDTO.setStatusCode( new StatusCodeDTO("200","Se han encontrado los siguientes asientos de avión disponibles"));
            }
            responseDTO.setFlightSeatsDTOSList(flightsFiltered);

        }

    }


    private void setMoneyCostFromBookingFlightSeat(BookFlightSeatResponseDTO bookFlightSeatResponseDTO, List<FlightSeatsDTO> flightsFiltered)
    {
        Double amount = Double.valueOf(flightsFiltered.get(0).getPrice().toString()) ;
        Double interest = bookFlightSeatResponseDTO.getInterest();

        bookFlightSeatResponseDTO.setAmount(amount);
        bookFlightSeatResponseDTO.setInterest(InterestUtils.calculateInterests(bookFlightSeatResponseDTO.getFlightReservation().getPaymentMethod().getDues()));
        bookFlightSeatResponseDTO.setTotal( Math.ceil(amount * interest));
    }

    private void setResponseFromBookFlightSeat(BookFlightSeatsRequestDTO bookFlightSeatsRequestDTO,
                                               BookFlightSeatResponseDTO bookFlightSeatResponseDTO)
    {

        List<FlightSeatsDTO> flightsFiltered;

        //Compruebo que no haya errores en la request de ningún tipo
        if (bookFlightSeatResponseDTO.getStatusCode().getCode().equalsIgnoreCase("200"))
        {
            //Aplicamos los filtros
            flightsFiltered = getFlightSeatsFiltered(bookFlightSeatsRequestDTO);

            //Si a pesar de ser válida la request no encontró ningún asiento de avión disponible
            if (flightsFiltered.size() < 1)
            {
                bookFlightSeatResponseDTO.setStatusCode( new StatusCodeDTO("200","No se encontro ningun asiento de avión disponible para las fechas indicadas"));
            }
            else //Si llegaste hasta acá es el camino feliz
            {
                bookFlightSeatResponseDTO.setStatusCode( new StatusCodeDTO("200","Se han encontrado asientos de avión disponibles"));

                setMoneyCostFromBookingFlightSeat(bookFlightSeatResponseDTO, flightsFiltered);
            }
        }
    }
}
