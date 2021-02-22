package com.mercadolibre.desafioquality;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class HotelRoomBookingTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    String url= "http://localhost:8080/api/v1/booking";
    String request =  url;



    @Test
    void shouldBookHotelRoomWithPaymentMethodCreditCard6Dues() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"12/06/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"credito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 6\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":524720.0,\"interest\":10.0,\"total\":5247200.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"12/06/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"credito\",\"number\":\"345345\",\"dues\":6}},\"statusCodeDTO\":{\"code\":\"200\",\"message\":\"el proceso termino satisfactoriamente\"}}"));
    }

    @Test
    void shouldBookHotelRoomWithPaymentMethodDebitCard() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"12/06/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 1\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":524720.0,\"interest\":1.0,\"total\":524720.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"12/06/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":1}},\"statusCodeDTO\":{\"code\":\"200\",\"message\":\"el proceso termino satisfactoriamente\"}}"));
    }


    @Test
    void shouldNOTBeAbleToBookHotelRoomWithPaymentMethodDebitCardMoreThanOneDue() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"12/06/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 4000\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"12/06/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":4000}},\"statusCodeDTO\":{\"code\":\"404\",\"message\":\"Se ha ingresado una cantidad de cuotas distinta de 1\"}}"));
    }


    @Test
    void shouldNOTBeAbleToBookHotelRoomWithWrongEmail() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"alice#@example.me.org\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"12/06/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 1\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"alice#@example.me.org\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"12/06/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":1}},\"statusCodeDTO\":{\"code\":\"404\",\"message\":\"Porfavor ingrese un e-mail válido\"}}"));
    }


    @Test
    void shouldNOTBeAbleToBookHotelRoomWhenPeopleListDoesntMatchPeopleAmount() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"12/06/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":20000,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 1\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"12/06/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":20000,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":1}},\"statusCodeDTO\":{\"code\":\"404\",\"message\":\"La cantidad de gente indicada debe coincidir con la lista de personas detallada\"}}"));
    }


    @Test
    void shouldNOTBeAbleToBookHotelRoomWhenDestinationIsNotFound() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"12/06/2021\",\n" +
                        "        \"destination\":\"Tenochitlan\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 1\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"12/06/2021\",\"destination\":\"Tenochitlan\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":1}},\"statusCodeDTO\":{\"code\":\"404\",\"message\":\"El destino elegido no existe\"}}"));
    }


    @Test
    void shouldNOTBeAbleToBookHotelRoomWhenDateFromIsPrevious() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"16/04/2021\",\n" +
                        "        \"dateTo\":\"12/06/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 1\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":0.0,\"interest\":1.0,\"total\":0.0,\"bookingDTO\":{\"dateFrom\":\"16/04/2021\",\"dateTo\":\"12/06/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":1}},\"statusCodeDTO\":{\"code\":\"200\",\"message\":\"No se encontro ningun cuarto de hotel disponible para las fechas indicadas\"}}"));
    }



    @Test
    void shouldNOTBeAbleToBookHotelRoomWhenLeavingDateIsLater() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"22/06/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 1\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":0.0,\"interest\":1.0,\"total\":0.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"22/06/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":1}},\"statusCodeDTO\":{\"code\":\"200\",\"message\":\"No se encontro ningun cuarto de hotel disponible para las fechas indicadas\"}}"));
    }


    @Test
    void shouldNOTBeAbleToBookHotelRoomWhenLeavingDateIsEarlierThanEnteringDate() throws  Exception {
        this.mockMvc.perform(get( request)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"arjonamiguel@gmail.com\",\n" +
                        "    \"booking\": {\n" +
                        "        \"dateFrom\":\"17/04/2021\",\n" +
                        "        \"dateTo\":\"12/01/2021\",\n" +
                        "        \"destination\":\"Cartagena\",\n" +
                        "        \"hotelCode\":\"BG/0004\",\n" +
                        "        \"peopleAmount\":2,\n" +
                        "        \"roomType\":\"Múltiple\",\n" +
                        "        \"people\": [\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Pepito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\": \"10/11/1982\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"dni\":\"12345678\",\n" +
                        "                \"name\":\"Fulanito\",\n" +
                        "                \"lastname\": \"Gomez\",\n" +
                        "                \"birthDate\":\"10/11/1983\",\n" +
                        "                \"mail\":\"arjonamiguel@gmail.com\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    , \n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\":\"debito\",\n" +
                        "        \"number\":\"345345\",\n" +
                        "        \"dues\": 1\n" +
                        "    }\n" +
                        "    }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"arjonamiguel@gmail.com\",\"amount\":0.0,\"interest\":0.0,\"total\":0.0,\"bookingDTO\":{\"dateFrom\":\"17/04/2021\",\"dateTo\":\"12/01/2021\",\"destination\":\"Cartagena\",\"hotelCode\":\"BG/0004\",\"peopleAmount\":2,\"roomType\":\"Múltiple\",\"people\":[{\"dni\":\"12345678\",\"name\":\"Pepito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1982\",\"mail\":\"arjonamiguel@gmail.com\"},{\"dni\":\"12345678\",\"name\":\"Fulanito\",\"lastname\":\"Gomez\",\"birthDate\":\"10/11/1983\",\"mail\":\"arjonamiguel@gmail.com\"}],\"paymentMethod\":{\"type\":\"debito\",\"number\":\"345345\",\"dues\":1}},\"statusCodeDTO\":{\"code\":\"404\",\"message\":\"La fecha de salida debe ser mayor a la de entrada\"}}"));
    }

    //Se podrían agregar más tests, algunos que fallen con 400 genérico, otros que verifiquen como se calculan los precios, y demás que se me ocurran

}
