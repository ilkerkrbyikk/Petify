package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Breed;
import com.Ilker.Petify.exception.BreedAlreadyExistException;
import com.Ilker.Petify.exception.BreedNotFoundException;
import com.Ilker.Petify.repository.BreedRepository;
import com.Ilker.Petify.request.breed.AddBreedRequest;
import com.Ilker.Petify.request.breed.UpdateBreedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;

    @Override
    public List<Breed> getAll() {
        return breedRepository.findAll();
    }

    @Override
    public Breed add(AddBreedRequest request) {
        isBreedExistsByName(request.getName());
        Breed breed = new Breed();
        breed.setName(request.getName());
        return breedRepository.save(breed);
    }

    @Override
    public Breed update(UpdateBreedRequest request, Long id) {
        Breed breed = breedRepository
                .getBreedById(id);
        breed.setName(request.getName());

        return breedRepository.save(breed);
    }

    @Override
    public void delete(Long id) {
        isBreedExistsById(id);
        breedRepository.deleteById(id);
    }

    public void isBreedExistsByName(String name){
        Optional<Breed> optional = Optional.ofNullable(breedRepository.getBreedByName(name));
        if(optional.isPresent()){
            throw new BreedAlreadyExistException("Breed already exists with name: " + name);
        }
    }
    public void isBreedExistsById(Long id){
        Optional<Breed> optional = Optional.ofNullable(breedRepository.getBreedById(id));
        if(optional.isEmpty()){
            throw new BreedNotFoundException("Breed not found with ID: " + id);
        }
    }
}
