package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> getCustomerByEmail(String email);

    Customer findCustomerByEmail(String email);
}
