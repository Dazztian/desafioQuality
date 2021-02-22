package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DAO.Impl.BookingDaoImpl;
import com.mercadolibre.desafioquality.DTO.*;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class BookRequestValidation {


    public static boolean isValidDate(BookHotelRoomRequestDTO request) {
        if (request.getBookingDTO().getDateFrom() != null && request.getBookingDTO().getDateTo() != null)
            return request.getBookingDTO().getDateFrom().before(request.getBookingDTO().getDateTo());

        return true;
    }

    public static boolean isValidDestination(BookHotelRoomRequestDTO request) {
        if (request.getBookingDTO().getDestination() != null) {
            BookingDaoImpl apiBusqueda = new BookingDaoImpl();
            List<HotelDTO> matches = apiBusqueda.getAllHotels();
            return matches.stream().anyMatch(hotel -> hotel.getDestination().equalsIgnoreCase(request.getBookingDTO().getDestination().toLowerCase(Locale.ROOT)));
        }

        return true;
    }

    public static boolean isValidAmountOfPeople(BookHotelRoomRequestDTO request) {

        //Comparo que la cantidad de gente coincida con la que aparece en la lista
        return request.getBookingDTO().getPeopleAmount() == request.getBookingDTO().getPeople().size()
                && request.getBookingDTO().getPeopleAmount() != null
                && request.getBookingDTO().getPeople() != null;
    }

    public static boolean isValidEmail(BookHotelRoomRequestDTO request) {
        if (request.getUsername() != null) {
            String email = request.getUsername();

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
    public static boolean isValidDebitCard(BookHotelRoomRequestDTO request) {
        return request.getPaymentMethodDTO().getDues() == 1;
    }


    public static boolean isValidCreditCard(BookHotelRoomRequestDTO request) {
        //No tengo manera de checkear el tema del interes ya que no viene dado en la request
        return true;
    }

    public static BookHotelRoomResponseDTO validatePaymentMethod(BookHotelRoomRequestDTO request)
    {
        if (request.getPaymentMethodDTO() != null)
        {
            if (request.getPaymentMethodDTO().getType().equalsIgnoreCase("debito"))
            {
                if (isValidDebitCard(request))
                {
                    Double interestRecharge = 1.0;
                    return new BookHotelRoomResponseDTO(request.getUsername(), 0.0, interestRecharge, 0.0,
                            request.getBookingDTO(), new StatusCodeDTO("200", "el proceso termino satisfactoriamente"));
                }
                else
                    { return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "Se ha ingresado una cantidad de cuotas distinta de 1")); }
            }

            if (request.getPaymentMethodDTO().getType().equalsIgnoreCase("credito"))
            {
                if (isValidCreditCard(request))
                {
                    Double interestRecharge = 5.0 * request.getPaymentMethodDTO().getDues() % 3;
                    return new BookHotelRoomResponseDTO(request.getUsername(), 0.0, interestRecharge, 0.0,
                            request.getBookingDTO(), new StatusCodeDTO("200", "el proceso termino satisfactoriamente"));
                }
                else
                    { return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "El porcentaje debe ser: NNNNNN y el monto de interés: MMMMMM")); }
            }
       }
        //Si no nos informan el método de pago entonces devolvemos false
        return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "ERROR"));
    }


        public static BookHotelRoomResponseDTO validateRequest (BookHotelRoomRequestDTO request)
        {

            //¿Está bien que devuelva un 404 o debería devolver otro código?
            //Como un 400 genérico

            if (!isValidEmail(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "Porfavor ingrese un e-mail válido"));

            if (!isValidAmountOfPeople(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "La cantidad de gente indicada debe coincidir con la lista de personas detallada"));

            if (!isValidDestination(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "El destino elegido no existe"));

            if (!isValidDate(request))
                return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "La fecha de salida debe ser mayor a la de entrada"));

            //Acá resolvemos toda la validación de los  método de pago
            validatePaymentMethod(request);

            //Informamos ERROR genérico si no cumplió con los medios de pagos y no se trata de ningún error en particular
            return new ErrorResponseDTO(request.getUsername(), request.getBookingDTO(), new StatusCodeDTO("404", "ERROR"));

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


