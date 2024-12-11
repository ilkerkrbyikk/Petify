package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.entity.CorporateCustomer;
import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.exception.BarberNotFoundException;
import com.Ilker.Petify.repository.CityRepository;
import com.Ilker.Petify.repository.CorporateCustomerRepository;
import com.Ilker.Petify.repository.PetBarberRepository;
import com.Ilker.Petify.request.barber.AddPetBarberRequest;
import com.Ilker.Petify.request.barber.UpdatePetBarberRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetBarberServiceImpl implements PetBarberService{

    private static final Logger logger = LoggerFactory.getLogger(PetBarberServiceImpl.class);

    private final PetBarberRepository petBarberRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final CityRepository cityRepository;

    @Override
    public PetBarber add(AddPetBarberRequest request) {
        logger.info("Adding new pet barber with name: {}", request.getName());
        PetBarber petBarber = new PetBarber();
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(request.getCorporateCustomerId()).get();
        City city = cityRepository.getCityById(request.getCityId());

        petBarber.setCity(city);
        petBarber.setName(request.getName());
        petBarber.setPrice(request.getPrice());
        petBarber.setPhoneNumber(request.getPhoneNumber());
        petBarber.setAddress(request.getAddress());
        petBarber.setCorporateCustomer(corporateCustomer);

        logger.info("Pet barber added successfully with ID: {}", petBarber.getId());
        return petBarberRepository.save(petBarber);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete pet barber with ID: {}", id);
        isPetBarberExistsById(id);
        petBarberRepository.deleteById(id);
        logger.info("Pet barber with ID: {} deleted successfully", id);
    }

    @Override
    public PetBarber update(UpdatePetBarberRequest request, Long id) {
        logger.info("Updating pet barber with ID: {}", id);
        isPetBarberExistsById(id);
        PetBarber petBarber = petBarberRepository.findById(id).get();
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(request.getCorporateCustomerId()).get();
        City city = cityRepository.getCityById(request.getCityId());

        petBarber.setCity(city);
        petBarber.setName(request.getName());
        petBarber.setPrice(request.getPrice());
        petBarber.setPhoneNumber(request.getPhoneNumber());
        petBarber.setAddress(request.getAddress());
        petBarber.setCorporateCustomer(corporateCustomer);

        logger.info("Pet barber updated successfully with ID: {}", petBarber.getId());
        return petBarberRepository.save(petBarber);
    }

    @Override
    public List<PetBarber> getAll() {
        logger.info("Fetching all pet barbers");
        return petBarberRepository.findAll();
    }

    @Override
    public List<PetBarber> getByCity(Long cityId){
        logger.info("Fetching pet barbers for city ID: {}", cityId);
        return petBarberRepository.findByCityId(cityId);
    }

    public void isPetBarberExistsById(Long id){
        Optional<PetBarber> optional = petBarberRepository.findById(id);
        if(optional.isEmpty()){
            logger.error("Pet barber not found with ID: {}", id);
            throw new BarberNotFoundException("Barber not found with ID: " + id);
        }
    }
}