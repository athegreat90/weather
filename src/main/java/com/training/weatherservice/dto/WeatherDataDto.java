package com.training.weatherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDataDto
{
    private long id;
    private LocalDate date;
    private LocationDto location;
    private List<Double> temperature;
}
