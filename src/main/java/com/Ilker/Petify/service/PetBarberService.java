package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.request.barber.AddPetBarberRequest;
import com.Ilker.Petify.request.barber.UpdatePetBarberRequest;

import java.util.List;

public interface PetBarberService {

    PetBarber add(AddPetBarberRequest request);
    void delete(Long id);
    PetBarber update(UpdatePetBarberRequest request, Long id);
    List<PetBarber> getAll();
    List<PetBarber> getByCity(Long cityId);
}
