package com.Ilker.Petify.repository;


import com.Ilker.Petify.entity.ReHoming;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReHomingRepository extends JpaRepository<ReHoming,Long> {
    ReHoming findRehomingById(Long id);

    Optional<ReHoming> findPetById(Long id);
}