package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Breed;
import com.Ilker.Petify.exception.BreedAlreadyExistException;
import com.Ilker.Petify.exception.BreedNotFoundException;
import com.Ilker.Petify.repository.BreedRepository;
import com.Ilker.Petify.request.breed.AddBreedRequest;
import com.Ilker.Petify.request.breed.UpdateBreedRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BreedServiceImpl implements BreedService {

    private static final Logger logger = LoggerFactory.getLogger(BreedServiceImpl.class);

    private final BreedRepository breedRepository;

    @Override
    public List<Breed> getAll() {
        logger.info("Fetching all breeds");
        return breedRepository.findAll();
    }

    @Override
    public Breed add(AddBreedRequest request) {
        logger.info("Attempting to add breed with name: {}", request.getName());
        isBreedExistsByName(request.getName());
        Breed breed = new Breed();
        breed.setName(request.getName());
        logger.info("Breed added successfully with name: {}", breed.getName());
        return breedRepository.save(breed);
    }

    @Override
    public Breed update(UpdateBreedRequest request, Long id) {
        logger.info("Attempting to update breed with ID: {}", id);
        isBreedExistsById(id);
        Breed breed = breedRepository
                .getBreedById(id);
        breed.setName(request.getName());

        logger.info("Breed updated successfully with ID: {} and new name: {}", id, breed.getName());
        return breedRepository.save(breed);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete breed with ID: {}", id);
        isBreedExistsById(id);
        breedRepository.deleteById(id);
        logger.info("Breed with ID: {} deleted successfully", id);
    }

    public void isBreedExistsByName(String name){
        Optional<Breed> optional = Optional.ofNullable(breedRepository.getBreedByName(name));
        if(optional.isPresent()){
            logger.error("Breed already exists with name: {}", name);
            throw new BreedAlreadyExistException("Breed already exists with name: " + name);
        }
    }
    public void isBreedExistsById(Long id){
        Optional<Breed> optional = Optional.ofNullable(breedRepository.getBreedById(id));
        if(optional.isEmpty()){
            logger.error("Breed not found with ID: {}", id);
            throw new BreedNotFoundException("Breed not found with ID: " + id);
        }
    }
}
