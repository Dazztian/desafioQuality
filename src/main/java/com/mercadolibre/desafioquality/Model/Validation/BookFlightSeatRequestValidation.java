package com.mercadolibre.desafioquality.Model.Validation;


import com.mercadolibre.desafioquality.DAO.FlightDAO.Impl.FlightSeatsDAOImpl;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.ErrorResponseDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

//Esto estaría bueno resolverlo con generics o herencia e indicar como cada clase que hereda de un validation
//Implementa el método validte y de qué manera
public class BookFlightSeatRequestValidation {

    public static boolean isValidDate(BookFlightSeatsRequestDTO request) {
        if (request.getFlightReservation().getDateFrom() != null && request.getFlightReservation().getDateTo() != null)
            return request.getFlightReservation().getDateFrom().before(request.getFlightReservation().getDateTo());

        return true;
    }

    public static boolean isValidDestination(BookFlightSeatsRequestDTO request) {
        if (request.getFlightReservation().getDestination() != null) {
            FlightSeatsDAOImpl apiBusqueda = new FlightSeatsDAOImpl();
            List<FlightSeatsDTO> matches = apiBusqueda.getAllFlightSeats();
            return matches.stream().anyMatch(flightSeat -> flightSeat.getDestination().equalsIgnoreCase(request.getFlightReservation().getDestination().toLowerCase(Locale.ROOT)));
        }

        return true;
    }

    public static boolean isValidOrigin(BookFlightSeatsRequestDTO request) {
        if (request.getFlightReservation().getOrigin() != null) {
            FlightSeatsDAOImpl apiBusqueda = new FlightSeatsDAOImpl();
            List<FlightSeatsDTO> matches = apiBusqueda.getAllFlightSeats();
            return matches.stream().anyMatch(flightSeat -> flightSeat.getOrigin().equalsIgnoreCase(request.getFlightReservation().getOrigin().toLowerCase(Locale.ROOT)));
        }

        return true;
    }


    public static boolean isValidAmountOfPeople(BookFlightSeatsRequestDTO request) {

        //Comparo que la cantidad de gente coincida con la que aparece en la lista
        return request.getFlightReservation().getSeats() == request.getFlightReservation().getPeople().size()
                && request.getFlightReservation().getSeats() != null
                && request.getFlightReservation().getPeople() != null;
    }

    public static boolean isValidEmail(BookFlightSeatsRequestDTO request) {
        if (request.getUserName() != null) {
            String email = request.getUserName();

            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);

            return pat.matcher(email).matches();

        }
        //Si el username es null devolvemos false, xq necesitamos 1 mail
        return false;
    }


    //¿De dónde checkeas los datos extra de la tarjeta si sólo aparecen en la response?
    public static boolean isValidDebitCard(BookFlightSeatsRequestDTO request) {
        return request.getFlightReservation().getPaymentMethod().getDues() == 1;
    }


    public static boolean isValidCreditCard(BookFlightSeatsRequestDTO request) {
        //No tengo manera de checkear el tema del interes ya que no viene dado en la request
        return true;
    }

    public static BookFlightSeatResponseDTO validatePaymentMethod(BookFlightSeatsRequestDTO request)
    {
        if (request.getFlightReservation().getPaymentMethod() != null)
        {
            if (request.getFlightReservation().getPaymentMethod().getType().equalsIgnoreCase("debito"))
            {
                if (isValidDebitCard(request))
                {
                    Double interestRecharge = 1.0;
                    return new BookFlightSeatResponseDTO(request.getUserName(), 0.0, interestRecharge, 0.0,
                            request.getFlightReservation(), new StatusCodeDTO("200", "el proceso termino satisfactoriamente"));
                }
                else
                { return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "Se ha ingresado una cantidad de cuotas distinta de 1")); }
            }

            if (request.getFlightReservation().getPaymentMethod().getType().equalsIgnoreCase("credito"))
            {
                if (isValidCreditCard(request))
                {
                    //Lo dividimos por 100 ya que se trata de un PORCENTAJE
                    Double interestPorcentage =  (5.0 * ( 1 + Math.floor( (request.getFlightReservation().getPaymentMethod().getDues() / 3) ) ) /100);
                    Double interestRecharge = 1 +interestPorcentage;
                    return new BookFlightSeatResponseDTO(request.getUserName(), 0.0, interestRecharge, 0.0,
                            request.getFlightReservation(), new StatusCodeDTO("200", "el proceso termino satisfactoriamente"));
                }
                else
                { return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "El porcentaje debe ser: NNNNNN y el monto de interés: MMMMMM")); }
            }
        }
        //Si no nos informan el método de pago entonces devolvemos ERROR
        return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "ERROR, debe informar un medio de pago"));
    }


    public static BookFlightSeatResponseDTO validateRequest (BookFlightSeatsRequestDTO request)
    {

        if (!isValidEmail(request))
            return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "Porfavor ingrese un e-mail válido"));

        if (!isValidAmountOfPeople(request))
            return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "La cantidad de gente indicada debe coincidir con la lista de personas detallada"));

        if (!isValidDestination(request))
            return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "El destino elegido no existe"));

        if (!isValidOrigin(request))
            return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "El Origen elegido no existe"));


        if (!isValidDate(request))
            return new ErrorResponseDTO(request.getUserName(), request.getFlightReservation(), new StatusCodeDTO("404", "La fecha de salida debe ser mayor a la de entrada"));

        //Acá resolvemos toda la validación de los  método de pago
        return validatePaymentMethod(request);

    }

}


