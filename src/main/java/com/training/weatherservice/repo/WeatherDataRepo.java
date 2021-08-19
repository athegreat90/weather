package com.training.weatherservice.repo;

import com.training.weatherservice.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherDataRepo extends JpaRepository<WeatherData, Long>
{
    @Query(value = "SELECT * FROM WEATHER_DATA WHERE date = ?1", nativeQuery = true)
    List<WeatherData> getWeatherDataByDate(LocalDate date);
}
