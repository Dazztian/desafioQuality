package com.mercadolibre.desafioquality.Model.Validation;

import com.mercadolibre.desafioquality.DAO.FlightDAO.Impl.FlightSeatsDAOImpl;
import com.mercadolibre.desafioquality.DAO.HotelRoomDAO.Impl.BookingDaoImpl;
import com.mercadolibre.desafioquality.DTO.AvailabilityHotelRoomDTOs.HotelDTO;
import com.mercadolibre.desafioquality.DTO.FlightSeatsDtos.FlightSeatsDTO;
import com.mercadolibre.desafioquality.DTO.PeopleDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Validator {

    static FlightSeatsDAOImpl apiBusqueda = new FlightSeatsDAOImpl();
    static List<FlightSeatsDTO> matches = apiBusqueda.getAllFlightSeats();


    public static boolean isValidDate(Date dateFrom, Date dateTo) {
        if (dateFrom != null && dateTo != null)
            return dateFrom.before(dateTo);

        return true;
    }

    public static boolean isValidDestination(String destination) {
        if (destination != null) {
            BookingDaoImpl apiBusqueda = new BookingDaoImpl();
            List<HotelDTO> matches = apiBusqueda.getAllHotels();
            return matches.stream().anyMatch(hotel -> hotel.getDestination().equalsIgnoreCase(destination.toLowerCase(Locale.ROOT)));
        }

        return true;
    }

    //Este es para vuelos
    public static boolean isValidOrigin(String origin)
    {
        if (origin != null)
        {
            return matches.stream().anyMatch( hotel -> hotel.getOrigin().equalsIgnoreCase(origin.toLowerCase(Locale.ROOT)));
        }

        return true;
    }


    public static boolean isValidButEmpty(Object ...args)
    {
        return Arrays.stream(args).allMatch( param -> param ==null);

    }

    public static boolean isValidAmountOfPeople(Integer amount, List<PeopleDTO> peopleList) {

        return amount == peopleList.size()
                && amount != null
                && peopleList != null;
    }

    public static boolean isValidEmail(String mail) {
        if (mail != null) {
            String email = mail;

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
    public static boolean isValidDebitCard(Integer dues) {
        return dues == 1;
    }

    //No tengo manera de checkear el tema del interes ya que no viene dado en la request
    //Podría no recibir nada ya que de momento no esta comprobando nada
    public static boolean isValidCreditCard() {

        return true;
    }
}
