package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.request.city.AddCityRequest;
import com.Ilker.Petify.request.city.UpdateCityRequest;

import java.util.List;

public interface CityService {

    List<City> getAll();
    City add(AddCityRequest request);
    City update(UpdateCityRequest request, Long id);
    void delete(Long id);
}
