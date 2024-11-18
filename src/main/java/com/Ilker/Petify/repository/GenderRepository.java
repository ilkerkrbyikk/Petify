package com.Ilker.Petify.repository;


import com.Ilker.Petify.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender,Long> {
    Gender getGenderById(Long genderId);
}
