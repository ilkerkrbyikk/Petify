package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.entity.PetSitter;
import com.Ilker.Petify.exception.PetSitterNotFoundException;
import com.Ilker.Petify.repository.PetSitterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetSitterServiceImpl implements PetSitterService {

    private static final Logger logger = LoggerFactory.getLogger(PetSitterServiceImpl.class);

    private final PetSitterRepository petSitterRepository;

    public List<PetSitter> getAll(){
        logger.info("Fetching all pet sitters");
        return petSitterRepository.findAll();
    }

    public Optional<List<PetSitter>> getByCity(Long cityId){
        logger.info("Fetching pet sitters for city ID: {}", cityId);
        City city = new City();
        city.setId(cityId);
        Optional<List<PetSitter>> optional = Optional.ofNullable(petSitterRepository.findByCity(city));
        if(optional.isEmpty()){
            logger.error("No pet sitter found in city ID: {}", cityId);
            throw new PetSitterNotFoundException("No pet sitter found in that city. " + city.getName());
        }

        logger.info("Found {} pet sitters in city ID: {}", optional.get().size(), cityId);
        return optional;
    }



}
