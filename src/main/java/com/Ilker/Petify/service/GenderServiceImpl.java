package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Gender;
import com.Ilker.Petify.repository.GenderRepository;
import com.Ilker.Petify.request.gender.AddGenderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public List<Gender> getAll() {
        return genderRepository.findAll();
    }

    @Override
    public Gender add(AddGenderRequest request) {
        Gender gender = new Gender();
        gender.setName(request.getName());

        return genderRepository.save(gender);
    }

    @Override
    public void delete(Long id) {
        genderRepository.deleteById(id);
    }
}
