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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetBarberServiceImpl implements PetBarberService{

    private final PetBarberRepository petBarberRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final CityRepository cityRepository;

    @Override
    public PetBarber add(AddPetBarberRequest request) {
        PetBarber petBarber = new PetBarber();
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(request.getCorporateCustomerId()).get();
        City city = cityRepository.getCityById(request.getCityId());

        petBarber.setCity(city);
        petBarber.setName(request.getName());
        petBarber.setPrice(request.getPrice());
        petBarber.setPhoneNumber(request.getPhoneNumber());
        petBarber.setAddress(request.getAddress());
        petBarber.setCorporateCustomer(corporateCustomer);
        return petBarberRepository.save(petBarber);
    }

    @Override
    public void delete(Long id) {
        isPetBarberExistsById(id);
        petBarberRepository.deleteById(id);
    }

    @Override
    public PetBarber update(UpdatePetBarberRequest request, Long id) {
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
        return petBarberRepository.save(petBarber);
    }

    @Override
    public List<PetBarber> getAll() {
        return petBarberRepository.findAll();
    }

    @Override
    public List<PetBarber> getByCity(Long cityId){
        return petBarberRepository.findByCityId(cityId);
    }

    public void isPetBarberExistsById(Long id){
        Optional<PetBarber> optional = petBarberRepository.findById(id);
        if(optional.isEmpty()){
            throw new BarberNotFoundException("Barber not found with ID: " + id);
        }
    }
}