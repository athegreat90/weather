package com.training.weatherservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationDtoTest
{
    LocationDto dto;
    @BeforeEach
    void setUp()
    {
        dto = LocationDto.builder().id(1).lon(1.0D).lat(1.0D).city("Demo").state("Demo").build();
    }
    @Test
    void getId()
    {
        assertEquals(1, dto.getId());
    }

    @Test
    void getLat()
    {
        assertEquals(1.0D, dto.getLat());
    }

    @Test
    void getLon()
    {
        assertEquals(1.0D, dto.getLon());
    }

    @Test
    void getCity()
    {
        assertEquals("Demo", dto.getCity());
    }

    @Test
    void getState()
    {
        assertEquals("Demo", dto.getState());
    }

    @Test
    void setId()
    {
        dto.setId(2);
        assertEquals(2, dto.getId());
    }

    @Test
    void setLat()
    {
        dto.setLat(2.0D);
        assertEquals(2.0D, dto.getLat());
    }

    @Test
    void setLon()
    {
        dto.setLon(2.0D);
        assertEquals(2.0D, dto.getLon());
    }

    @Test
    void setCity()
    {
        dto.setCity("DEMO");
        assertEquals("DEMO", dto.getCity());
    }

    @Test
    void setState()
    {
        dto.setState("DEMO");
        assertEquals("DEMO", dto.getState());
    }
}