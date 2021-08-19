package com.training.weatherservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseGenericDtoTest
{

    ResponseGenericDto<String> dto;

    @BeforeEach
    void setUp()
    {
        dto = new ResponseGenericDto<>(0, "OK", "DEMO");
    }

    @Test
    void getCode()
    {
        assertEquals(0, dto.getCode());
    }

    @Test
    void setCode()
    {
        dto.setCode(1);
        assertEquals(1, dto.getCode());
    }

    @Test
    void getMessage()
    {
        assertEquals("OK", dto.getMessage());
    }

    @Test
    void setMessage()
    {
        dto.setMessage("FAIL");
        assertEquals("FAIL", dto.getMessage());
    }

    @Test
    void getBody()
    {
        assertEquals("DEMO", dto.getBody());
    }

    @Test
    void setBody()
    {
        dto.setBody("CHANGED");
        assertEquals("CHANGED", dto.getBody());
    }

    @Test
    void testToString()
    {
        assertEquals("ResponseGenericDto{code=0, message='OK', body=DEMO}", dto.toString());
    }
}