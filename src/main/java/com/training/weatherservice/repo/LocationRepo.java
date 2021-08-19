package com.training.weatherservice.repo;

import com.training.weatherservice.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long>
{
    @Query(value = "SELECT * FROM location WHERE lat = ?1 AND lon = ?2 AND city = ?3 AND state = ?4", nativeQuery = true)
    Location getLocationByLatAndLonAndCityAndState(double lat, double lon, String city, String state);
}
