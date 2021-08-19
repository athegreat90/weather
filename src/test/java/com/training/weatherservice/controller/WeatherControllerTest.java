package com.training.weatherservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WeatherControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    @BeforeEach
    void setUp()
    {
        mapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void getAll() throws Exception
    {
        String url = "/api/v1/weather";
        ResultActions perform = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

        String urlDate = "/api/v1/weather?date=2020-09-15";
        ResultActions performDate = mockMvc.perform(get(urlDate).contentType(MediaType.APPLICATION_JSON));


        String urlNotFound = "/api/v1/weather/?date=2019-09-15";
        ResultActions performNotFound = mockMvc.perform(get(urlNotFound).contentType(MediaType.APPLICATION_JSON));

        assertAll(() ->
        {
            perform.andExpect(status().isOk());
            performDate.andExpect(status().isOk());
            performNotFound.andExpect(status().isNotFound());
        });
    }

    @Test
    @Order(2)
    void getById() throws Exception
    {
        String url = "/api/v1/weather/1";
        ResultActions perform = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));
        perform.andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void postData() throws Exception
    {
        var bodyRaw = "{\n" +
                "    \"date\": \"2021-06-04\",\n" +
                "    \"location\": {\n" +
                "        \"lat\": 35.7589,\n" +
                "        \"lon\": 96.7970,\n" +
                "        \"city\": \"FW\",\n" +
                "        \"state\": \"Texas\"\n" +
                "    },\n" +
                "    \"temperature\": [\n" +
                "        87.2,\n" +
                "        89.5,\n" +
                "        95.3,\n" +
                "        96.6,\n" +
                "        99.1\n" +
                "    ]\n" +
                "}";
        String url = "/api/v1/weather";
        ResultActions perform = mockMvc.perform(post(url).content(bodyRaw).contentType(MediaType.APPLICATION_JSON));
        perform.andExpect(status().is(201));
    }

    @Test
    @Order(4)
    void deleteTest() throws Exception
    {
        String url = "/api/v1/weather/1";
        ResultActions perform = mockMvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON));

        String urlNotFound = "/api/v1/weather/2";
        ResultActions performNotFound = mockMvc.perform(delete(urlNotFound).contentType(MediaType.APPLICATION_JSON));

        String urlAll = "/api/v1/weather";
        ResultActions performAll = mockMvc.perform(delete(urlAll).contentType(MediaType.APPLICATION_JSON));

        assertAll(() ->
        {
            perform.andExpect(status().isOk());
            performNotFound.andExpect(status().isOk());
            performAll.andExpect(status().isOk());
        });
        int v[][][] = null;
    }
}