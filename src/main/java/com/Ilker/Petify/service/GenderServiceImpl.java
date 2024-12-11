package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Gender;
import com.Ilker.Petify.repository.GenderRepository;
import com.Ilker.Petify.request.gender.AddGenderRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private static final Logger logger = LoggerFactory.getLogger(GenderServiceImpl.class);

    private final GenderRepository genderRepository;

    @Override
    public List<Gender> getAll() {
        logger.info("Fetching all genders");
        return genderRepository.findAll();
    }

    @Override
    public Gender add(AddGenderRequest request) {
        logger.info("Attempting to add gender with name: {}", request.getName());
        Gender gender = new Gender();
        gender.setName(request.getName());

        logger.info("Gender added successfully with name: {}", gender.getName());
        return genderRepository.save(gender);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete gender with ID: {}", id);
        genderRepository.deleteById(id);
        logger.info("Gender with ID: {} deleted successfully", id);
    }
}
