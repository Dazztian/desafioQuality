package com.mercadolibre.desafioquality;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightSeatsBookingTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    String url= "http://localhost:8080/api/v1/flight-reservation";
    String request =  url;


    @Test
    void shouldBookFlightSeatSuccesfully() throws  Exception {
        this.mockMvc.perform(post( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\":\"julio@gmail.com\",\n" +
                        "    \"flightReservation\": {\n" +
                        "        \"dateFrom\":\"10/02/2021\",\n" +
                        "        \"dateTo\":\"24/02/2021\",\n" +
                        "        \"origin\":\"Bogotá\",\n" +
                        "        \"destination\":\"Medellín\",\n" +
                        "        \"flightNumber\":\"BOME-4442\",\n" +
                        "        \"seats\":2,\n" +
                        "        \"seatType\":\"Economy\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\":\"gonzalez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\":\"gonzalez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \"paymentMethod\": {\n" +
                        "        \"type\":\"credito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\":6\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":11000.0,\"interest\":15.0,\"total\":12650.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"200\",\"message\":\"Se han encontrado asientos de avión disponibles\"}}"));
    }

    @Test
    void shouldNOTBookFlightSeatSuccesfullyDestinationDoesNotExists() throws  Exception {
        this.mockMvc.perform(post( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\":\"julio@gmail.com\",\n" +
                        "    \"flightReservation\": {\n" +
                        "        \"dateFrom\":\"10/02/2021\",\n" +
                        "        \"dateTo\":\"24/02/2021\",\n" +
                        "        \"origin\":\"Bogotá\",\n" +
                        "        \"destination\":\"Scerze\",\n" +
                        "        \"flightNumber\":\"BOME-4442\",\n" +
                        "        \"seats\":2,\n" +
                        "        \"seatType\":\"Economy\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\":\"gonzalez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\":\"gonzalez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \"paymentMethod\": {\n" +
                        "        \"type\":\"credito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\":6\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Scerze\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"404\",\"message\":\"El destino elegido no existe\"}}"));
    }


    @Test
    void shouldNOTBookFlightSeatSuccesfullyOriginDoesNotExists() throws  Exception {
        this.mockMvc.perform(post( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\":\"julio@gmail.com\",\n" +
                        "    \"flightReservation\": {\n" +
                        "        \"dateFrom\":\"10/02/2021\",\n" +
                        "        \"dateTo\":\"24/02/2021\",\n" +
                        "        \"origin\":\"Azen\",\n" +
                        "        \"destination\":\"Medellín\",\n" +
                        "        \"flightNumber\":\"BOME-4442\",\n" +
                        "        \"seats\":2,\n" +
                        "        \"seatType\":\"Economy\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\":\"gonzalez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\":\"gonzalez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \"paymentMethod\": {\n" +
                        "        \"type\":\"credito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\":6\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Azen\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"404\",\"message\":\"El Origen elegido no existe\"}}"));
    }
}
