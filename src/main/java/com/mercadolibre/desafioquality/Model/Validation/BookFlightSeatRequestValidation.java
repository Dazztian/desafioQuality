package com.mercadolibre.desafioquality.Model.Validation;


import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.BookFlightSeatsRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookFlightSeatsDTO.ErrorResponseDTO;
import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import com.mercadolibre.desafioquality.utils.InterestUtils;


public class BookFlightSeatRequestValidation {

    public static boolean isValidDate(BookFlightSeatsRequestDTO request)
    {
        return Validator.isValidDate(request.getFlightReservation().getDateFrom(), request.getFlightReservation().getDateTo());

    }

    public static boolean isValidDestination(BookFlightSeatsRequestDTO request)
    {
        return Validator.isValidDestination(request.getFlightReservation().getDestination());
    }

    public static boolean isValidOrigin(BookFlightSeatsRequestDTO request)
    {
        return Validator.isValidOrigin(request.getFlightReservation().getOrigin());
    }


    public static boolean isValidAmountOfPeople(BookFlightSeatsRequestDTO request)
    {
        return Validator.isValidAmountOfPeople( request.getFlightReservation().getSeats(), request.getFlightReservation().getPeople());
    }

    public static boolean isValidEmail(BookFlightSeatsRequestDTO request)
    {
        return Validator.isValidEmail(request.getUserName());
    }


    public static boolean isValidDebitCard(BookFlightSeatsRequestDTO request) {
        return Validator.isValidDebitCard(request.getFlightReservation().getPaymentMethod().getDues());
    }


    public static boolean isValidCreditCard(BookFlightSeatsRequestDTO request) {
        return Validator.isValidCreditCard();
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
                    //Double interestPorcentage =  (5.0 * ( 1 + Math.floor( (request.getFlightReservation().getPaymentMethod().getDues() / 3) ) ) /100);
                    Double interestPorcentage = InterestUtils.calculatePorcentageInterests(request.getFlightReservation().getPaymentMethod().getDues());
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


    //Podría hacer que devuelva algo "base" y si está bien le agrego "cositas"
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


