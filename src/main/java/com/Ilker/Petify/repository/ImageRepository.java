package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
