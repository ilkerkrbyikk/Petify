package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.entity.PetSitter;
import com.Ilker.Petify.exception.PetSitterNotFoundException;
import com.Ilker.Petify.repository.PetSitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetSitterServiceImpl implements PetSitterService {

    private final PetSitterRepository petSitterRepository;

    public List<PetSitter> getAll(){
        return petSitterRepository.findAll();
    }

    public Optional<List<PetSitter>> getByCity(Long cityId){
        City city = new City();
        city.setId(cityId);
        Optional<List<PetSitter>> optional = Optional.ofNullable(petSitterRepository.findByCity(city));
        if(optional.isEmpty()){
            throw new PetSitterNotFoundException("No pet sitter found in that city. " + city.getName());
        }
        return optional;
    }



}
