package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Gender;
import com.Ilker.Petify.request.gender.AddGenderRequest;

import java.util.List;

public interface GenderService {

    List<Gender> getAll();

    Gender add(AddGenderRequest request);
    void delete(Long id);
}
