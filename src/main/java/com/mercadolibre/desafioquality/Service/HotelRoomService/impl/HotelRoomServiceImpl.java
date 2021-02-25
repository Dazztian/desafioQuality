package com.mercadolibre.desafioquality.Service.HotelRoomService.impl;

import com.mercadolibre.desafioquality.DAO.HotelRoomDAO.Impl.BookingDaoImpl;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.HotelDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.RequestDTO;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.ResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;
import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import com.mercadolibre.desafioquality.Model.FilterFactory.BookHotelRoomFilter;
import com.mercadolibre.desafioquality.Model.FilterFactory.HotelFilter;
import com.mercadolibre.desafioquality.Model.Validation.AvailabilityRequestValidation;
import com.mercadolibre.desafioquality.Model.Validation.BookHotelRoomRequestValidation;
import com.mercadolibre.desafioquality.Service.HotelRoomService.HotelRoomService;
import com.mercadolibre.desafioquality.utils.DateUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HotelRoomServiceImpl implements HotelRoomService {

    BookingDaoImpl apiBusqueda = new BookingDaoImpl();

    public HotelRoomServiceImpl() {
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
}
