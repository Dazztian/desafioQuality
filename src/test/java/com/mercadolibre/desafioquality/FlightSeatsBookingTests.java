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
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":11000.0,\"interest\":10.0,\"total\":12101.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"200\",\"message\":\"Se han encontrado asientos de avión disponibles\"}}"));
    }

    @Test
    void shouldNOTBookFlightSeatSuccesfullyyWhenDestinationDoesNotExists() throws  Exception {
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
    void shouldNOTBookFlightSeatSuccesfullyWhenOriginDoesNotExists() throws  Exception {
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

    @Test
    void shouldNOTBookFlightSeatSuccesfullyWhenMailIsNotValid() throws  Exception {
        this.mockMvc.perform(post( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\":\"jul####@gmail.com\",\n" +
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
                .andExpect(content().json("{\"userName\":\"jul####@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"404\",\"message\":\"Porfavor ingrese un e-mail válido\"}}"));
    }


    @Test
    void shoulNOTdBookFlightSeatSuccesfullyWhenDateFromIsLaterThanDateTo() throws  Exception {
        this.mockMvc.perform(post( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\":\"julio@gmail.com\",\n" +
                        "    \"flightReservation\": {\n" +
                        "        \"dateFrom\":\"10/06/2021\",\n" +
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
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/06/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"404\",\"message\":\"La fecha de salida debe ser mayor a la de entrada\"}}"));
    }


    @Test
    void shoulnotdBookFlightSeatSuccesfullyWhenTypeSeatIsWrong() throws  Exception {
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
                        "        \"seatType\":\"SUPER ASIENTO\",\n" +
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
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":0.0,\"interest\":1.1,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"SUPER ASIENTO\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"200\",\"message\":\"No se encontro ningun asiento de avión disponible para las fechas indicadas\"}}"));
    }


    @Test
    void shouldNOTBookFlightSeatSuccesfullyWhenSeatsDoesNotMatchAmountOfPeopleInTheList() throws  Exception {
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
                        "        \"seats\":33333,\n" +
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
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":33333,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"404\",\"message\":\"La cantidad de gente indicada debe coincidir con la lista de personas detallada\"}}"));
    }


    @Test
    void shoulNotdBookFlightSeatSuccesfullyWhenTriesToPayInMoreThan1DueWithDebitCard() throws  Exception {
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
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\":6\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":6}},\"statusCode\":{\"code\":\"404\",\"message\":\"Se ha ingresado una cantidad de cuotas distinta de 1\"}}"));
    }


    @Test
    void shouldNotBookFlightSeatSuccesfullyWhenDoesntInformPaymentMethod() throws  Exception {
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
                        "    \n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"julio@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"flightReservation\":{\"flightNumber\":\"BOME-4442\",\"origin\":\"Bogotá\",\"destination\":\"Medellín\",\"seatType\":\"Economy\",\"seats\":2,\"dateFrom\":\"10/02/2021\",\"dateTo\":\"24/02/2021\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"gonzalez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":null},\"statusCode\":{\"code\":\"404\",\"message\":\"ERROR, debe informar un medio de pago\"}}"));
    }

}
