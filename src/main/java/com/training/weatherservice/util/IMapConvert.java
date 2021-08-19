package com.training.weatherservice.util;

import com.training.weatherservice.domain.Location;
import com.training.weatherservice.domain.WeatherData;
import com.training.weatherservice.dto.LocationDto;
import com.training.weatherservice.dto.WeatherDataDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IMapConvert
{
    LocationDto getLocationDtoFromEntity(Location location);
    Location getLocationFromDto(LocationDto locationDto);

    WeatherDataDto getWeatherDataDtoFromEntity(WeatherData weatherData);
    WeatherData getWeatherDataFromDto(WeatherDataDto weatherDataDto);

    WeatherData update(WeatherData updatedData);

    List<WeatherDataDto> getWeatherDataListFromEntities(List<WeatherData> weatherDataList);
}
