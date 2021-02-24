package com.mercadolibre.desafioquality.Service.impl;

import com.mercadolibre.desafioquality.DAO.FlightDAO.Impl.FlightSeatsDAOImpl;
import com.mercadolibre.desafioquality.DAO.HotelRoomDAO.Impl.BookingDaoImpl;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.HotelDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.RequestDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;
import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsResponseDTO;
import com.mercadolibre.desafioquality.Model.FilterFactory.BookFlightSeatFilter;
import com.mercadolibre.desafioquality.Model.FilterFactory.BookHotelRoomFilter;
import com.mercadolibre.desafioquality.Model.FilterFactory.FlightSeatsFilter;
import com.mercadolibre.desafioquality.Model.FilterFactory.HotelFilter;
import com.mercadolibre.desafioquality.Model.Validation.AvailabilityRequestValidation;
import com.mercadolibre.desafioquality.Model.Validation.BookHotelRoomRequestValidation;
import com.mercadolibre.desafioquality.Model.Validation.BookFlightSeatRequestValidation;
import com.mercadolibre.desafioquality.Model.Validation.FlightSeatsValidation;
import com.mercadolibre.desafioquality.Service.BookingService;
import com.mercadolibre.desafioquality.utils.DateUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {


    BookingDaoImpl apiBusqueda = new BookingDaoImpl();
    FlightSeatsDAOImpl apiBusquedaFlightSeats = new FlightSeatsDAOImpl();

    public BookingServiceImpl() { }

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
    public BookHotelRoomResponseDTO bookHotelRoom(BookHotelRoomRequestDTO bookHotelRoomRequestDTO)  {


        //1ero valido los datos
        BookHotelRoomResponseDTO bookHotelRoomResponseDTO= BookHotelRoomRequestValidation.validateRequest(bookHotelRoomRequestDTO);

        //Seteamos la response
        setResponseFromBookHotelRoom(bookHotelRoomRequestDTO, bookHotelRoomResponseDTO);

        return bookHotelRoomResponseDTO;
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

    
    //Es MUY parecido al método anterior que tiene el mismo nombre, 
    // podría meterle un refactor para usar sólo 1 
    @Override
    public List<FlightSeatsDTO> getFlightSeatsFiltered(FlightSeatsRequestDTO request) {

        List<FlightSeatsDTO>  matches = apiBusquedaFlightSeats.getAllFlightSeats();
        Predicate<FlightSeatsDTO> compositeFilterRule;
        compositeFilterRule = FlightSeatsFilter.getFlightSeatsFilter(request);


        return matches.stream()
                .filter(compositeFilterRule)
                .collect(Collectors.toList());
    }


    @Override
    public ResponseDTO getAllAvailableHotels(RequestDTO request) {

        ResponseDTO responseDTO = AvailabilityRequestValidation.validateRequest(request);

        //Modifico la response según lo q haya devuelto la validación de la request
        modifyResponse(request, responseDTO);

        return responseDTO;

    }

    @Override
    public List<HotelDTO> getHotelsFiltered(RequestDTO request)
    {

        List<HotelDTO>  matches = apiBusqueda.getAllHotels();
        Predicate<HotelDTO> compositeFilterRule;
        compositeFilterRule = HotelFilter.getHotelFilter(request);


        return matches.stream()
                .filter(compositeFilterRule)
                .collect(Collectors.toList());

    }

    @Override
    public List<HotelDTO> getHotelRoomsFiltered(BookHotelRoomRequestDTO bookHotelRoomRequestDTO)
    {

        List<HotelDTO>  matches = apiBusqueda.getAllHotels();
        Predicate<HotelDTO> compositeFilterRule;
        compositeFilterRule = BookHotelRoomFilter.getHotelRoomFilter(bookHotelRoomRequestDTO);


        return matches.stream()
                .filter(compositeFilterRule)
                .collect(Collectors.toList());

    }

    private void modifyResponse(RequestDTO request, ResponseDTO responseDTO) {

        List<HotelDTO> hotelsFiltered;

        //Compruebo que no haya errores en la request de ningún tipo
        if (responseDTO.getStatusCode().equalsIgnoreCase("200"))
        {
            hotelsFiltered = getHotelsFiltered(request);

            //Si a pesar de ser válida la request no encontró ningún destino disponible
            if (hotelsFiltered.size() < 1)
                responseDTO.setMessage("No se encontro ningun destino disponible para las fechas indicadas");

            responseDTO.setHotelList(hotelsFiltered);
        }
    }

    private void setResponseFromBookHotelRoom(BookHotelRoomRequestDTO request, BookHotelRoomResponseDTO responseDTO) {

        List<HotelDTO> hotelsFiltered;

        //Compruebo que no haya errores en la request de ningún tipo
        if (responseDTO.getStatusCodeDTO().getCode().equalsIgnoreCase("200"))
        {
            //Aplicamos los filtros
            hotelsFiltered = getHotelRoomsFiltered(request);

            //Si a pesar de ser válida la request no encontró ningún destino disponible
            if (hotelsFiltered.size() < 1)
            {
                responseDTO.setStatusCodeDTO( new StatusCodeDTO("200","No se encontro ningun cuarto de hotel disponible para las fechas indicadas"));
            }
            else //Si llegaste hasta acá es el camino feliz
            {
                setMoneyCostsFromBookingHotelRoom(responseDTO, hotelsFiltered);
            }
        }
    }

    private void setMoneyCostsFromBookingHotelRoom(BookHotelRoomResponseDTO responseDTO, List<HotelDTO> hotelsFiltered) {
        Double price = Double.valueOf(hotelsFiltered.get(0).getPrice().toString()) ;
        Double interest = responseDTO.getInterest();
        Double amount =price * DateUtils.getDaysDifference(responseDTO.getBookingDTO().getDateFrom(),
                responseDTO.getBookingDTO().getDateTo());

        responseDTO.setAmount(amount);
        responseDTO.setInterest(interest);
        responseDTO.setTotal(amount * interest);
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

    private void setMoneyCostFromBookingFlightSeat(BookFlightSeatResponseDTO bookFlightSeatResponseDTO, List<FlightSeatsDTO> flightsFiltered)
    {
        Double amount = Double.valueOf(flightsFiltered.get(0).getPrice().toString()) ;
        Double interest = bookFlightSeatResponseDTO.getInterest();

        bookFlightSeatResponseDTO.setAmount(amount);
        //De esta manera mostramos porcentualmente el interes, un 15% se vería como 15
        bookFlightSeatResponseDTO.setInterest( Math.ceil( (interest-1) *100) );
        bookFlightSeatResponseDTO.setTotal( Math.ceil(amount * interest));
    }

}
