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
class HotelRoomAvailabilityTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	String url= "http://localhost:8080/api/v1/hotels";

	@Test
	void getAllAvailableHotels() throws Exception {
		String request =  url;
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
						"    \"statusCode\": \"200\",\n" +
						"    \"message\": \"request sin parametros, mostrando todos los destinos disponibles\",\n" +
						"    \"hotelList\": [\n" +
						"        {\n" +
						"            \"code\": \"CH/0002\",\n" +
						"            \"name\": \"Cataratas Hotel\",\n" +
						"            \"destination\": \"Puerto Iguazú\",\n" +
						"            \"roomType\": \"Doble\",\n" +
						"            \"price\": 6300,\n" +
						"            \"disponibilityFrom\": \"10/02/2021\",\n" +
						"            \"disponibilityUntil\": \"20/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"CH/0003\",\n" +
						"            \"name\": \"Cataratas Hotel 2\",\n" +
						"            \"destination\": \"Puerto Iguazú\",\n" +
						"            \"roomType\": \"Triple\",\n" +
						"            \"price\": 8200,\n" +
						"            \"disponibilityFrom\": \"10/02/2021\",\n" +
						"            \"disponibilityUntil\": \"23/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"HB/0001\",\n" +
						"            \"name\": \"Hotel Bristol\",\n" +
						"            \"destination\": \"Buenos Aires\",\n" +
						"            \"roomType\": \"Single\",\n" +
						"            \"price\": 5435,\n" +
						"            \"disponibilityFrom\": \"10/02/2021\",\n" +
						"            \"disponibilityUntil\": \"19/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"BH/0002\",\n" +
						"            \"name\": \"Hotel Bristol 2\",\n" +
						"            \"destination\": \"Buenos Aires\",\n" +
						"            \"roomType\": \"Doble\",\n" +
						"            \"price\": 7200,\n" +
						"            \"disponibilityFrom\": \"12/02/2021\",\n" +
						"            \"disponibilityUntil\": \"17/04/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"SH/0002\",\n" +
						"            \"name\": \"Sheraton\",\n" +
						"            \"destination\": \"Tucumán\",\n" +
						"            \"roomType\": \"Doble\",\n" +
						"            \"price\": 5790,\n" +
						"            \"disponibilityFrom\": \"17/04/2021\",\n" +
						"            \"disponibilityUntil\": \"23/05/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"SH/0001\",\n" +
						"            \"name\": \"Sheraton 2\",\n" +
						"            \"destination\": \"Tucumán\",\n" +
						"            \"roomType\": \"Single\",\n" +
						"            \"price\": 4150,\n" +
						"            \"disponibilityFrom\": \"02/01/2021\",\n" +
						"            \"disponibilityUntil\": \"19/02/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"SE/0001\",\n" +
						"            \"name\": \"Selina\",\n" +
						"            \"destination\": \"Bogotá\",\n" +
						"            \"roomType\": \"Single\",\n" +
						"            \"price\": 3900,\n" +
						"            \"disponibilityFrom\": \"23/01/2021\",\n" +
						"            \"disponibilityUntil\": \"23/11/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"SE/0002\",\n" +
						"            \"name\": \"Selina 2\",\n" +
						"            \"destination\": \"Bogotá\",\n" +
						"            \"roomType\": \"Doble\",\n" +
						"            \"price\": 5840,\n" +
						"            \"disponibilityFrom\": \"23/01/2021\",\n" +
						"            \"disponibilityUntil\": \"15/10/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"EC/0003\",\n" +
						"            \"name\": \"El Campín\",\n" +
						"            \"destination\": \"Bogotá\",\n" +
						"            \"roomType\": \"Triple\",\n" +
						"            \"price\": 7020,\n" +
						"            \"disponibilityFrom\": \"15/02/2021\",\n" +
						"            \"disponibilityUntil\": \"27/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"CP/0004\",\n" +
						"            \"name\": \"Central Plaza\",\n" +
						"            \"destination\": \"Medellín\",\n" +
						"            \"roomType\": \"Múltiple\",\n" +
						"            \"price\": 8600,\n" +
						"            \"disponibilityFrom\": \"01/03/2021\",\n" +
						"            \"disponibilityUntil\": \"17/04/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"CP/0002\",\n" +
						"            \"name\": \"Central Plaza 2\",\n" +
						"            \"destination\": \"Medellín\",\n" +
						"            \"roomType\": \"Doble\",\n" +
						"            \"price\": 6400,\n" +
						"            \"disponibilityFrom\": \"10/02/2021\",\n" +
						"            \"disponibilityUntil\": \"20/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"BG/0004\",\n" +
						"            \"name\": \"Bocagrande\",\n" +
						"            \"destination\": \"Cartagena\",\n" +
						"            \"roomType\": \"Múltiple\",\n" +
						"            \"price\": 9370,\n" +
						"            \"disponibilityFrom\": \"17/04/2021\",\n" +
						"            \"disponibilityUntil\": \"12/06/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        }\n" +
						"    ]\n" +
						"}"));
	}


	@Test
	void shouldGetAvailableHotelRoomForDestinationPuertoIguazu() throws  Exception {

		String request =  url.concat("?destination=Puerto Iguazú");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
						"    \"statusCode\": \"200\",\n" +
						"    \"message\": \"Se han ingresado datos validos\",\n" +
						"    \"hotelList\": [\n" +
						"        {\n" +
						"            \"code\": \"CH/0002\",\n" +
						"            \"name\": \"Cataratas Hotel\",\n" +
						"            \"destination\": \"Puerto Iguazú\",\n" +
						"            \"roomType\": \"Doble\",\n" +
						"            \"price\": 6300,\n" +
						"            \"disponibilityFrom\": \"10/02/2021\",\n" +
						"            \"disponibilityUntil\": \"20/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        },\n" +
						"        {\n" +
						"            \"code\": \"CH/0003\",\n" +
						"            \"name\": \"Cataratas Hotel 2\",\n" +
						"            \"destination\": \"Puerto Iguazú\",\n" +
						"            \"roomType\": \"Triple\",\n" +
						"            \"price\": 8200,\n" +
						"            \"disponibilityFrom\": \"10/02/2021\",\n" +
						"            \"disponibilityUntil\": \"23/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        }\n" +
						"    ]\n" +
						"}"));
	}


	@Test
	void shouldGetOnlyOneAvailableHotelRoomForDestinationPuertoIguazuInSpecificDate() throws  Exception {

		String request =  url.concat("?destination=Puerto Iguazú&dateFrom=02/10/2021&dateTo=03/20/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
						"    \"statusCode\": \"200\",\n" +
						"    \"message\": \"Se han ingresado datos validos\",\n" +
						"    \"hotelList\": [\n" +
						"        {\n" +
						"            \"code\": \"CH/0003\",\n" +
						"            \"name\": \"Cataratas Hotel 2\",\n" +
						"            \"destination\": \"Puerto Iguazú\",\n" +
						"            \"roomType\": \"Triple\",\n" +
						"            \"price\": 8200,\n" +
						"            \"disponibilityFrom\": \"10/02/2021\",\n" +
						"            \"disponibilityUntil\": \"23/03/2021\",\n" +
						"            \"ocuppied\": false\n" +
						"        }\n" +
						"    ]\n" +
						"}"));
	}



	@Test
	void shouldNotGetAvailableHotelForLaterDate() throws  Exception {

		String request =  url.concat("?destination=Puerto Iguazú&dateFrom=05/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
						"    \"statusCode\": \"200\",\n" +
						"    \"message\": \"No se encontro ningun destino disponible para las fechas indicadas\",\n" +
						"    \"hotelList\": []\n" +
						"}"));
	}

	@Test
	void shouldNotGetAvailableHotelRoomInitDateGreaterThanEndDate() throws  Exception {

		String request =  url.concat("?destination=Puerto Iguazú&dateFrom=05/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
						"    \"statusCode\": \"200\",\n" +
						"    \"message\": \"No se encontro ningun destino disponible para las fechas indicadas\",\n" +
						"    \"hotelList\": []\n" +
						"}"));
	}


}
