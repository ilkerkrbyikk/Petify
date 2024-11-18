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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final CityRepository cityRepository;
    private final BreedRepository breedRepository;
    private final GenderRepository genderRepository;


    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet add(AddPetRequest request) throws IOException {
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

        return petRepository.save(pet);
    }

    @Override
    public Pet update(UpdatePetRequest request, Long id) {
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

        return petRepository.save(pet);
    }



    @Override
    public void delete(Long id) {
        isPetExistsById(id);
        petRepository.deleteById(id);

    }

    @Override
    public Optional<List<Pet>> getByGender(Long id){
        Optional<List<Pet>> optional = Optional.ofNullable(petRepository.findByGenderId(id));

        if(optional.isEmpty()){
            throw new PetNotFoundException("Pet not found with this gender. ");
        }

        return optional;
    }

    @Override
    public Optional<List<Pet>> getByCity(Long id){
        Optional<List<Pet>> optional = Optional.ofNullable(petRepository.findByCityId(id));

        if(optional.isEmpty()){
            throw new PetNotFoundException("Pet not found in this city. ");
        }

        return optional;
    }

    @Override
    public Optional<List<Pet>> getByBreed(Long id){
        Optional<List<Pet>> optional = Optional.ofNullable(petRepository.findByBreedId(id));

        if(optional.isEmpty()){
            throw new PetNotFoundException("Pet not found with breed. ");
        }

        return optional;
    }

    public void isPetExistsById(Long id){
        Optional<Pet> optional = petRepository.findPetById(id);
        if(optional.isEmpty()){
            throw new PetNotFoundException("Pet not found with ID: " + id);
        }
    }
}