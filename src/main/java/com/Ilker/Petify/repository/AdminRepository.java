package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> getAdminByEmail(String email);

    Admin findByEmail(String email);

    Admin getByEmail(String email);
}
