package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.exception.CityAlreadyExistsException;
import com.Ilker.Petify.exception.CityNotFoundException;
import com.Ilker.Petify.repository.CityRepository;
import com.Ilker.Petify.request.city.AddCityRequest;
import com.Ilker.Petify.request.city.UpdateCityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City add(AddCityRequest request) {
        isCityExistsByName(request.getName());
        City city = new City();

        city.setName(request.getName());
        return cityRepository.save(city);
    }

    @Override
    public City update(UpdateCityRequest request, Long id) {
        isCityExistsById(id);
        City city = cityRepository.getCityById(id);
        city.setName(request.getName());

        return cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        isCityExistsById(id);
        cityRepository.deleteById(id);
    }

    public void isCityExistsById(Long id){
        Optional<City> optional = cityRepository.findCityById(id);
        if(optional.isEmpty()){
            throw new CityNotFoundException("City not found with ID: " + id);
        }
    }

    public void isCityExistsByName(String name){
        Optional<City> optional = cityRepository.getCityByName(name);
        if(optional.isPresent()){
            throw new CityAlreadyExistsException("City already exists with name: " + name);
        }
    }
}
