package com.training.weatherservice.service.impl;

import com.training.weatherservice.domain.WeatherData;
import com.training.weatherservice.dto.WeatherDataDto;
import com.training.weatherservice.repo.LocationRepo;
import com.training.weatherservice.repo.WeatherDataRepo;
import com.training.weatherservice.service.WeatherService;
import com.training.weatherservice.util.IMapConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class WeatherServiceImpl implements WeatherService
{
    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN, Locale.US);

    private final WeatherDataRepo weatherDataRepo;
    private final LocationRepo locationRepo;

    private IMapConvert weatherMapper = Mappers.getMapper(IMapConvert.class);

    @Transactional
    @Override
    public List<WeatherDataDto> getAll()
    {
        var dbResult = this.weatherDataRepo.findAll();
        var listWeather = sortingWeatherData(dbResult);
        return this.weatherMapper.getWeatherDataListFromEntities(listWeather);
    }

    private List<WeatherData> sortingWeatherData(List<WeatherData> dbResult)
    {
        return dbResult.stream().sorted(Comparator.comparingLong(WeatherData::getId)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public WeatherDataDto getById(Long id)
    {
        if (id == null)
        {
            return null;
        }
        var optionalWeatherData = this.weatherDataRepo.findById(id);
        if (optionalWeatherData.isPresent())
        {
            return this.weatherMapper.getWeatherDataDtoFromEntity(optionalWeatherData.get());
        }
        return null;
    }

    @Transactional
    @Override
    public List<WeatherDataDto> getByDate(String date)
    {
        var timeSearch = LocalDate.parse(date, FORMATTER);
        var listResult = this.weatherDataRepo.getWeatherDataByDate(timeSearch);
        if (listResult.isEmpty())
        {
            return List.of();
        }
        var listWeather = sortingWeatherData(listResult);
        return this.weatherMapper.getWeatherDataListFromEntities(listWeather);
    }

    @Transactional
    @Override
    public String save(WeatherDataDto body)
    {
        var location = this.locationRepo.getLocationByLatAndLonAndCityAndState(body.getLocation().getLat(), body.getLocation().getLon(), body.getLocation().getCity(), body.getLocation().getState());
        if (location != null)
        {
            return null;
        }
        var entity = weatherMapper.getWeatherDataFromDto(body);
        var result = weatherDataRepo.save(entity);
        return String.format("The registry saved with the id: %s", result.getId());
    }

    @Transactional
    @Override
    public WeatherDataDto delete(Long id)
    {
        var optionalWeatherData = this.weatherDataRepo.findById(id);
        if (optionalWeatherData.isPresent())
        {
            var response = this.weatherMapper.getWeatherDataDtoFromEntity(optionalWeatherData.get());
            this.weatherDataRepo.deleteById(id);
            return response;
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        this.weatherDataRepo.deleteAll();
    }
}
