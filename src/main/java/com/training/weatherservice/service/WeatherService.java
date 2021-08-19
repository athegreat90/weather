package com.training.weatherservice.service;

import com.training.weatherservice.dto.WeatherDataDto;

import java.util.List;

public interface WeatherService
{
    List<WeatherDataDto> getAll();
    WeatherDataDto getById(Long id);
    List<WeatherDataDto> getByDate(String date);

    String save(WeatherDataDto body);
    WeatherDataDto delete(Long id);
    void deleteAll();
}
