package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomRequestDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.BookHotelRoomResponseDTO;
import com.mercadolibre.desafioquality.DTO.BookHotelRoomDTOs.ErrorResponseDTO;
import com.mercadolibre.desafioquality.DTO.StatusCodeDTO;
import com.mercadolibre.desafioquality.utils.InterestUtils;


public class BookHotelRoomRequestValidation  {


    public static boolean isValidDate(BookHotelRoomRequestDTO request)
    {
        return Validator.isValidDate(request.getBooking().getDateFrom(), request.getBooking().getDateTo());
    }

    public static boolean isValidDestination(BookHotelRoomRequestDTO request)
    {
        return Validator.isValidDestination(request.getBooking().getDestination());
    }

    public static boolean isValidAmountOfPeople(BookHotelRoomRequestDTO request)
    {
        return Validator.isValidAmountOfPeople(request.getBooking().getPeopleAmount(), request.getBooking().getPeople());
    }

    public static boolean isValidEmail(BookHotelRoomRequestDTO request)
    {
        return  Validator.isValidEmail(request.getUsername());
    }


    public static boolean isValidDebitCard(BookHotelRoomRequestDTO request)
    {
        return Validator.isValidDebitCard(request.getBooking().getPaymentMethod().getDues());
    }


    public static boolean isValidCreditCard(BookHotelRoomRequestDTO request)
    {
        return Validator.isValidCreditCard();
    }

    //Estaría más piola q devuelva un PaymentStatusDTO o algo así y hacerlo genérico para ambos casos
    public static BookHotelRoomResponseDTO validatePaymentMethod(BookHotelRoomRequestDTO request)
    {
        if (request.getBooking().getPaymentMethod() != null)
        {
            if (request.getBooking().getPaymentMethod().getType().equalsIgnoreCase("debito"))
            {
                if (isValidDebitCard(request))
                {
                    Double interestRecharge = 1.0;
                    return new BookHotelRoomResponseDTO(request.getUsername(), 0.0, interestRecharge, 0.0,
                            request.getBooking(), new StatusCodeDTO("200", "el proceso termino satisfactoriamente"));
                }
                else
                    //Esto estaría bueno que exista la clase "error" con sus distintas subclases que espefican cuando/donde lanzarse para cada una
                    { return new ErrorResponseDTO(request.getUsername(), request.getBooking(), new StatusCodeDTO("404", "Se ha ingresado una cantidad de cuotas distinta de 1")); }
            }

            if (request.getBooking().getPaymentMethod().getType().equalsIgnoreCase("credito"))
            {
                if (isValidCreditCard(request))
                {

                    Double interestPercentage = InterestUtils.calculatePorcentageInterests(request.getBooking().getPaymentMethod().getDues());
                    Double interestRecharge = 1 + interestPercentage;
                    return new BookHotelRoomResponseDTO(request.getUsername(), 0.0, interestRecharge, 0.0,
                            request.getBooking(), new StatusCodeDTO("200", "el proceso termino satisfactoriamente"));
                }
                else
                    { return new ErrorResponseDTO(request.getUsername(), request.getBooking(), new StatusCodeDTO("404", "El porcentaje debe ser: NNNNNN y el monto de interés: MMMMMM")); }
            }
       }
        //Si no nos informan el método de pago entonces devolvemos ERROR
        return new ErrorResponseDTO(request.getUsername(), request.getBooking(), new StatusCodeDTO("404", "ERROR, debe informar un medio de pago"));
    }


    public static BookHotelRoomResponseDTO validateRequest (BookHotelRoomRequestDTO request)
    {


            if (!isValidEmail(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBooking(), new StatusCodeDTO("404", "Porfavor ingrese un e-mail válido"));

            if (!isValidAmountOfPeople(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBooking(), new StatusCodeDTO("404", "La cantidad de gente indicada debe coincidir con la lista de personas detallada"));

            if (!isValidDestination(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBooking(), new StatusCodeDTO("404", "El destino elegido no existe"));

            if (!isValidDate(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBooking(), new StatusCodeDTO("404", "La fecha de salida debe ser mayor a la de entrada"));

            //Acá resolvemos toda la validación de los  método de pago
            return validatePaymentMethod(request);

        }

}


    /* para probar la validez del mail
    public static void main(String[] args) {

        List<String> mailList = new ArrayList<>();

        mailList.add("alice@example.com");
        mailList.add("alice.bob@example.co.in");
        mailList.add("alice1@example.me.org");
        mailList.add("alice_bob@example.com");
        mailList.add("alice-bob@example.com");
        mailList.add("@example.com");
        mailList.add("alice&example.com");
        mailList.add("alice#@example.me.org");
        mailList.add("alice.bob@example.co.in");

        for(String mail: mailList)
            System.out.println(isValidEmail(mail));
    }*/


