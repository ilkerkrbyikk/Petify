package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Admin;
import com.Ilker.Petify.entity.CorporateCustomer;
import com.Ilker.Petify.entity.Customer;
import com.Ilker.Petify.entity.PetSitter;
import com.Ilker.Petify.exception.*;
import com.Ilker.Petify.repository.AdminRepository;
import com.Ilker.Petify.repository.CorporateCustomerRepository;
import com.Ilker.Petify.repository.CustomerRepository;
import com.Ilker.Petify.repository.PetSitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final AdminRepository adminRepository;
    private final PetSitterRepository petSitterRepository;
    private final CustomerRepository customerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;


    @Override
    public void isAdminExistsByEmail(String email) {
        Optional<Admin> optional = adminRepository.getAdminByEmail(email);
        if (optional.isPresent()) {
            throw new AdminAlreadyExists("Admin already exists with email: " + email);
        }
    }

    @Override
    public void checkAdminExistsByEmail(String email) {
        Optional<Admin> optional = adminRepository.getAdminByEmail(email);
        if (optional.isEmpty()) {
            throw new AdminNotFoundException("Admin not found with email: " + email);
        }
    }

    @Override
    public void isPetSitterExistsByEmail(String email) {
        Optional<PetSitter> optional = petSitterRepository.getPetSitterByEmail(email);
        if (optional.isPresent()) {
            throw new PetSitterAlreadyExistsException("Pet Sitter already exists with email: " + email);
        }
    }

    @Override
    public void checkPetSitterExistsByEmail(String email) {
        Optional<PetSitter> optional = petSitterRepository.getPetSitterByEmail(email);
        if (optional.isEmpty()) {
            throw new PetSitterNotFoundException("Pet Sitter not found with email: " + email);
        }
    }

    @Override
    public void isCustomerExistsByEmail(String email) {
        Optional<Customer> optional = customerRepository.getCustomerByEmail(email);
        if (optional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + email);
        }
    }

    @Override
    public void checkCustomerExistsByEmail(String email) {
        Optional<Customer> optional = customerRepository.getCustomerByEmail(email);
        if (optional.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found with email: " + email);
        }
    }

    @Override
    public void isCorporateCustomerExistsByEmail(String email) {
        Optional<CorporateCustomer> optional = corporateCustomerRepository.getCorporateCustomerByEmail(email);
        if (optional.isPresent()) {
            throw new CorporateCustomerAlreadyExistsException("Corporate customer already exists with email: " + email);
        }
    }

    @Override
    public void checkCorporateCustomerExistsByEmail(String email) {
        Optional<CorporateCustomer> optional = Optional.ofNullable(corporateCustomerRepository.findCorporateCustomerByEmail(email));
        if (optional.isEmpty()) {
            throw new CorporateCustomerNotFoundException("Corporate Customer not found with email: " + email);
        }
    }
}