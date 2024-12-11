package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Breed;
import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.entity.Gender;
import com.Ilker.Petify.entity.Pet;
import com.Ilker.Petify.exception.PetNotFoundException;
import com.Ilker.Petify.repository.BreedRepository;
import com.Ilker.Petify.repository.CityRepository;
import com.Ilker.Petify.repository.GenderRepository;
import com.Ilker.Petify.repository.PetRepository;
import com.Ilker.Petify.request.pet.AddPetRequest;
import com.Ilker.Petify.request.pet.UpdatePetRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

    private final PetRepository petRepository;
    private final CityRepository cityRepository;
    private final BreedRepository breedRepository;
    private final GenderRepository genderRepository;


    @Override
    public List<Pet> getAll() {
        logger.info("Fetching all pets");
        return petRepository.findAll();
    }

    @Override
    public Pet add(AddPetRequest request) throws IOException {
        logger.info("Adding new pet with name: {}", request.getName());
        City city = cityRepository.getCityById(request.getCityId());
        Breed breed = breedRepository.getBreedById(request.getBreedId());
        Gender gender = genderRepository.getGenderById(request.getGenderId());

        Pet pet = new Pet();
        pet.setName(request.getName());
        pet.setAge(request.getAge());
        pet.setCity(city);
        pet.setGender(gender);
        pet.setDescription(request.getDescription());
        pet.setWeight(request.getWeight());
        pet.setBreed(breed);

//        pet.setImageName(file.getOriginalFilename());
//        pet.setImageType(file.getContentType());
//        pet.setImageDate(file.getBytes());

//        Pet pet = this.modelMapperService.forRequest().map(request,Pet.class);

        logger.info("Pet added successfully with ID: {}", pet.getId());
        return petRepository.save(pet);
    }

    @Override
    public Pet update(UpdatePetRequest request, Long id) {
        logger.info("Updating pet with ID: {}", id);
        isPetExistsById(id);

        Breed breed = breedRepository.getBreedById(request.getBreedId());
        City city = cityRepository.getCityById(request.getCityId());
        Gender gender = genderRepository.getGenderById(request.getGenderId());

        Pet pet = petRepository.getPetById(id);
        pet.setBreed(breed);
        pet.setCity(city);
        pet.setName(request.getName());
        pet.setGender(gender);
        pet.setDescription(request.getDescription());
        pet.setAge(request.getAge());
        pet.setWeight(request.getWeight());

        logger.info("Pet updated successfully with ID: {}", pet.getId());
        return petRepository.save(pet);
    }



    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete pet with ID: {}", id);
        isPetExistsById(id);
        petRepository.deleteById(id);
        logger.info("Pet with ID: {} deleted successfully", id);

    }

    @Override
    public Optional<List<Pet>> getByGender(Long id){
        logger.info("Fetching pets by gender ID: {}", id);
        Optional<List<Pet>> optional = Optional.ofNullable(petRepository.findByGenderId(id));

        if(optional.isEmpty()){
            logger.error("No pets found with gender ID: {}", id);
            throw new PetNotFoundException("Pet not found with this gender. ");
        }

        return optional;
    }

    @Override
    public Optional<List<Pet>> getByCity(Long id){
        logger.info("Fetching pets by city ID: {}", id);
        Optional<List<Pet>> optional = Optional.ofNullable(petRepository.findByCityId(id));

        if(optional.isEmpty()){
            logger.error("No pets found in city ID: {}", id);
            throw new PetNotFoundException("Pet not found in this city. ");
        }

        return optional;
    }

    @Override
    public Optional<List<Pet>> getByBreed(Long id){
        logger.info("Fetching pets by breed ID: {}", id);
        Optional<List<Pet>> optional = Optional.ofNullable(petRepository.findByBreedId(id));

        if(optional.isEmpty()){
            logger.error("No pets found with breed ID: {}", id);
            throw new PetNotFoundException("Pet not found with breed. ");
        }

        return optional;
    }

    public void isPetExistsById(Long id){
        Optional<Pet> optional = petRepository.findPetById(id);
        if(optional.isEmpty()){
            logger.error("Pet not found with ID: {}", id);
            throw new PetNotFoundException("Pet not found with ID: " + id);
        }
    }
}