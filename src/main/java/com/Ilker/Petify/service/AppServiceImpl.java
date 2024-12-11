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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    private final AdminRepository adminRepository;
    private final PetSitterRepository petSitterRepository;
    private final CustomerRepository customerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;


    @Override
    public void isAdminExistsByEmail(String email) {
        logger.info("Checking if admin exists with email: {}", email);
        Optional<Admin> optional = adminRepository.getAdminByEmail(email);
        if (optional.isPresent()) {
            logger.warn("Admin already exists with email: {}", email);
            throw new AdminAlreadyExists("Admin already exists with email: " + email);
        }
        logger.info("No admin found with email: {}", email);

    }

    @Override
    public void checkAdminExistsByEmail(String email) {
        logger.info("Checking for admin existence with email: {}", email);
        Optional<Admin> optional = adminRepository.getAdminByEmail(email);
        if (optional.isEmpty()) {
            logger.error("Admin not found with email: {}", email);
            throw new AdminNotFoundException("Admin not found with email: " + email);
        }
        logger.info("Checking for admin existence with email: {}", email);
    }

    @Override
    public void isPetSitterExistsByEmail(String email) {
        logger.info("Checking if pet sitter exists with email: {}", email);
        Optional<PetSitter> optional = petSitterRepository.getPetSitterByEmail(email);
        if (optional.isPresent()) {
            throw new PetSitterAlreadyExistsException("Pet Sitter already exists with email: " + email);
        }
        logger.info("No pet sitter found with email: {}", email);

    }

    @Override
    public void checkPetSitterExistsByEmail(String email) {
        logger.info("Checking for pet sitter existence with email: {}", email);

        Optional<PetSitter> optional = petSitterRepository.getPetSitterByEmail(email);
        if (optional.isEmpty()) {
            logger.warn("Pet Sitter already exists with email: {}", email);
            throw new PetSitterNotFoundException("Pet Sitter not found with email: " + email);
        }
        logger.info("Pet Sitter found with email: {}", email);
    }

    @Override
    public void isCustomerExistsByEmail(String email) {
        logger.info("Checking if customer exists with email: {}", email);

        Optional<Customer> optional = customerRepository.getCustomerByEmail(email);
        if (optional.isPresent()) {
            logger.error("Pet Sitter not found with email: {}", email);
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + email);
        }
        logger.info("No customer found with email: {}", email);

    }

    @Override
    public void checkCustomerExistsByEmail(String email) {
        logger.info("Checking for customer existence with email: {}", email);

        Optional<Customer> optional = customerRepository.getCustomerByEmail(email);
        if (optional.isEmpty()) {
            logger.error("Customer not found with email: {}", email);
            throw new CustomerNotFoundException("Customer not found with email: " + email);
        }
        logger.info("Customer found with email: {}", email);

    }

    @Override
    public void isCorporateCustomerExistsByEmail(String email) {
        logger.info("Checking if corporate customer exists with email: {}", email);

        Optional<CorporateCustomer> optional = corporateCustomerRepository.getCorporateCustomerByEmail(email);
        if (optional.isPresent()) {
            logger.warn("Corporate customer already exists with email: {}", email);
            throw new CorporateCustomerAlreadyExistsException("Corporate customer already exists with email: " + email);
        }
        logger.info("No corporate customer found with email: {}", email);

    }

    @Override
    public void checkCorporateCustomerExistsByEmail(String email) {
        logger.info("Checking for corporate customer existence with email: {}", email);

        Optional<CorporateCustomer> optional = Optional.ofNullable(corporateCustomerRepository.findCorporateCustomerByEmail(email));
        if (optional.isEmpty()) {
            logger.error("Corporate Customer not found with email: {}", email);
            throw new CorporateCustomerNotFoundException("Corporate Customer not found with email: " + email);
        }
        logger.info("Corporate Customer found with email: {}", email);

    }
}