package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    City getCityById(Long id);

    Optional<City> findCityById(Long id);

    Optional<City> getCityByName(String name);
}