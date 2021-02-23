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
public class FlightSeatsTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    String url = "http://localhost:8080/api/v1/flights";
    String request = url;


    @Test
    void shouldBookHotelRoomWithPaymentMethodCreditCard6Dues() throws Exception {
        this.mockMvc.perform(get(request)
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
}
