package com.mercadolibre.desafioquality.Service.impl;

import com.mercadolibre.desafioquality.DAO.Impl.BookingDaoImpl;
import com.mercadolibre.desafioquality.DTO.*;
import com.mercadolibre.desafioquality.Model.HotelFilterFactory;
import com.mercadolibre.desafioquality.Model.AvailabilityRequestValidation;
import com.mercadolibre.desafioquality.Service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {


    BookingDaoImpl apiBusqueda = new BookingDaoImpl();

    public BookingServiceImpl() { }



    @Override
    public BookHotelRoomResponseDTO bookHotel(BookHotelRoomRequestDTO bookHotelRoomRequestDTO) {
        return null;
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
        compositeFilterRule = HotelFilterFactory.getHotelFilter(request);


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
}
