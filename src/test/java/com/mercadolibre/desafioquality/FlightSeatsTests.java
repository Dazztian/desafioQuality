package com.mercadolibre.desafioquality;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class FlightSeatsTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    String url = "http://localhost:8080/api/v1/flights";


    @Test
    void shouldGetAllFlightSeats() throws Exception {
        String request = url;
        this.mockMvc.perform(get(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\":{\"code\":\"200\",\"message\":\"Se han encontrado los siguientes asientos de avión disponibles\"},\"flightSeatsDTOSList\":[{\"flightNumber\":\"BAPI-1235\",\"origin\":\"Buenos Aires\",\"destination\":\"Puerto Iguazú\",\"seatType\":\"Economy\",\"price\":6500,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"15/02/2021\"},{\"flightNumber\":\"PIBA-1420\",\"origin\":\"Puerto Iguazú\",\"destination\":\"Bogotá\",\"seatType\":\"Business\",\"price\":43200,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"20/02/2021\"},{\"flightNumber\":\"PIBA-1420\",\"origin\":\"Puerto Iguazú\",\"destination\":\"Bogotá\",\"seatType\":\"Economy\",\"price\":25735,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"21/02/2021\"},{\"flightNumber\":\"BATU-5536\",\"origin\":\"Buenos Aires\",\"destination\":\"Tucumán\",\"seatType\":\"Economy\",\"price\":7320,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"17/02/2021\"},{\"flightNumber\":\"TUPI-3369\",\"origin\":\"Tucumán\",\"destination\":\"Puerto Iguazú\",\"seatType\":\"Business\",\"price\":12530,\"dateFrom\":\"12/02/2021\",\"dateTo\":\"23/02/2021\"},{\"flightNumber\":\"TUPI-3369\",\"origin\":\"Tucumán\",\"destination\":\"Puerto Iguazú\",\"seatType\":\"Economy\",\"price\":5400,\"dateFrom\":\"02/01/2021\",\"dateTo\":\"16/01/2021\"},{\"flightNumber\":\"BOCA-4213\",\"origin\":\"Bogotá\",\"destination\":\"Cartagena\",\"seatType\":\"Economy\",\"price\":8000,\"dateFrom\":\"23/01/2021\",\"dateTo\":\"05/02/2021\"},{\"flightNumber\":\"CAME-0321\",\"origin\":\"Cartagena\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"price\":7800,\"dateFrom\":\"23/01/2021\",\"dateTo\":\"31/01/2021\"},{\"flightNumber\":\"BOBA-6567\",\"origin\":\"Bogotá\",\"destination\":\"Buenos Aires\",\"seatType\":\"Business\",\"price\":57000,\"dateFrom\":\"15/02/2021\",\"dateTo\":\"28/02/2021\"},{\"flightNumber\":\"BOBA-6567\",\"origin\":\"Bogotá\",\"destination\":\"Buenos Aires\",\"seatType\":\"Economy\",\"price\":39860,\"dateFrom\":\"01/03/2021\",\"dateTo\":\"14/03/2021\"},{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"price\":11000,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\"},{\"flightNumber\":\"MEPI-9986\",\"origin\":\"Medellín\",\"destination\":\"Puerto Iguazú\",\"seatType\":\"Business\",\"price\":41640,\"dateFrom\":\"17/04/2021\",\"dateTo\":\"02/05/2021\"}]}"));
    }

    @Test
    void shouldNotFoundFlightSeatsToDestinationThatDoesntExists() throws Exception {
        String request = url.concat("?destination=namekusei");
        this.mockMvc.perform(get(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\":{\"code\":\"404\",\"message\":\"Destino no encontrado\"},\"flightSeatsDTOSList\":null}"));
    }

    @Test
    void shouldNotFoundFlightSeatsFromOriginThatDoesntExists() throws Exception {
        String request = url.concat("?origin=Eberron");
        this.mockMvc.perform(get(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\":{\"code\":\"404\",\"message\":\"Origen no encontrado\"},\"flightSeatsDTOSList\":null}"));
    }

    @Test
    void shouldGetFlightSeatsOriginBuenosAires() throws Exception {
        String request = url.concat("?origin=buenos aires");
        this.mockMvc.perform(get(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\":{\"code\":\"200\",\"message\":\"Se han encontrado los siguientes asientos de avión disponibles\"},\"flightSeatsDTOSList\":[{\"flightNumber\":\"BAPI-1235\",\"origin\":\"Buenos Aires\",\"destination\":\"Puerto Iguazú\",\"seatType\":\"Economy\",\"price\":6500,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"15/02/2021\"},{\"flightNumber\":\"BATU-5536\",\"origin\":\"Buenos Aires\",\"destination\":\"Tucumán\",\"seatType\":\"Economy\",\"price\":7320,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"17/02/2021\"}]}"));
    }

    @Test
    void shouldGetFlightSeatsDestinationCartagena() throws Exception {
        String request = url.concat("?destination=cartagena");
        this.mockMvc.perform(get(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\":{\"code\":\"200\",\"message\":\"Se han encontrado los siguientes asientos de avión disponibles\"},\"flightSeatsDTOSList\":[{\"flightNumber\":\"BOCA-4213\",\"origin\":\"Bogotá\",\"destination\":\"Cartagena\",\"seatType\":\"Economy\",\"price\":8000,\"dateFrom\":\"23/01/2021\",\"dateTo\":\"05/02/2021\"}]}"));
    }

    @Test
    void shouldNOTGetFlightSeatsWhenDateFromIsLaterThanDateTo() throws Exception {
        String request = url.concat("?origin=Tucumán&destination=Puerto Iguazú&dateFrom=02/11/2021&dateTo=16/01/2021");
        this.mockMvc.perform(get(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\":{\"code\":\"404\",\"message\":\"La fecha de salida debe ser mayor a la de entrada\"},\"flightSeatsDTOSList\":null}"));
    }

    @Test
    void shouldGetFlightSeatsWhenAllParamsMatch() throws Exception {
        String request = url.concat("?origin=Tucumán&destination=Puerto Iguazú&dateFrom=02/01/2021&dateTo=16/01/2021");
        this.mockMvc.perform(get(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"statusCode\":{\"code\":\"200\",\"message\":\"Se han encontrado los siguientes asientos de avión disponibles\"},\"flightSeatsDTOSList\":[{\"flightNumber\":\"TUPI-3369\",\"origin\":\"Tucumán\",\"destination\":\"Puerto Iguazú\",\"seatType\":\"Economy\",\"price\":5400,\"dateFrom\":\"02/01/2021\",\"dateTo\":\"16/01/2021\"}]}"));
    }

}
