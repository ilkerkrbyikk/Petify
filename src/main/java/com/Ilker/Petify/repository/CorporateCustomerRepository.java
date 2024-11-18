package com.Ilker.Petify.repository;


import com.Ilker.Petify.entity.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer,Long> {
    Optional<CorporateCustomer> getCorporateCustomerByEmail(String email);
    CorporateCustomer findCorporateCustomerByEmail(String email);
}