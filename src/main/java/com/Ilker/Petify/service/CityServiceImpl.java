package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.exception.CityAlreadyExistsException;
import com.Ilker.Petify.exception.CityNotFoundException;
import com.Ilker.Petify.repository.CityRepository;
import com.Ilker.Petify.request.city.AddCityRequest;
import com.Ilker.Petify.request.city.UpdateCityRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        logger.info("Fetching all cities");
        return cityRepository.findAll();
    }

    @Override
    public City add(AddCityRequest request) {
        logger.info("Attempting to add city with name: {}", request.getName());
        isCityExistsByName(request.getName());
        City city = new City();

        city.setName(request.getName());

        logger.info("City added successfully with name: {}", city.getName());

        return cityRepository.save(city);
    }

    @Override
    public City update(UpdateCityRequest request, Long id) {
        logger.info("Attempting to update city with ID: {}", id);
        isCityExistsById(id);
        City city = cityRepository.getCityById(id);
        city.setName(request.getName());

        logger.info("City updated successfully with ID: {} and new name: {}", id, city.getName());
        return cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete city with ID: {}", id);
        isCityExistsById(id);
        cityRepository.deleteById(id);
        logger.info("City with ID: {} deleted successfully", id);
    }

    public void isCityExistsById(Long id){
        Optional<City> optional = cityRepository.findCityById(id);
        if(optional.isEmpty()){
            logger.error("City not found with ID: {}", id);
            throw new CityNotFoundException("City not found with ID: " + id);
        }
    }

    public void isCityExistsByName(String name){
        Optional<City> optional = cityRepository.getCityByName(name);
        if(optional.isPresent()){
            logger.error("City already exists with name: {}", name);
            throw new CityAlreadyExistsException("City already exists with name: " + name);
        }
    }
}
