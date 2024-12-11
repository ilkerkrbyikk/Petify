package com.Ilker.Petify.service;

import com.Ilker.Petify.dto.*;
import com.Ilker.Petify.entity.*;
import com.Ilker.Petify.enums.Roles;
import com.Ilker.Petify.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final PetSitterRepository petSitterRepository;
    private final AppService appService;
    private final CityRepository cityRepository;


    public Admin signUpAdmin(RegisterAdminDto request){
        logger.info("Admin registration attempt for email: {}", request.getEmail());
        appService.isAdminExistsByEmail(request.getEmail());
        Admin admin = new Admin();
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Roles.ADMIN);

        Admin savedAdmin = adminRepository.save(admin);
        logger.info("Admin registered successfully with email: {}", savedAdmin.getEmail());
        return savedAdmin;
    }

    public Admin authenticateAdmin(LoginDto request){
        logger.info("Admin authentication attempt for email: {}", request.getEmail());
        appService.checkAdminExistsByEmail(request.getEmail());
        Admin admin = adminRepository.findByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        logger.info("Admin authenticated successfully with email: {}", admin.getEmail());
        return admin;
    }

    public PetSitter signUpPetSitter(RegisterPetSitterDto request){
        logger.info("Pet Sitter registration attempt for email: {}", request.getEmail());
        appService.isAdminExistsByEmail(request.getEmail());
        City city = cityRepository.getCityById(request.getCityId());

        PetSitter petSitter = new PetSitter();
        petSitter.setEmail(request.getEmail());
        petSitter.setPassword(passwordEncoder.encode(request.getPassword()));
        petSitter.setAge(request.getAge());
        petSitter.setCity(city);
        petSitter.setFirstName(request.getFirstName());
        petSitter.setLastName(request.getLastName());
        petSitter.setTckNo(request.getTckNo());
        petSitter.setPhoneNumber(request.getPhoneNumber());
        petSitter.setAvailable(true);
        petSitter.setRoles(Roles.PET_SITTER);

        logger.info("Pet Sitter registered successfully with email: {}", petSitter.getEmail());
        return petSitterRepository.save(petSitter);
    }

    public PetSitter authenticateSitter(LoginDto request){
        logger.info("Pet Sitter authentication attempt for email: {}", request.getEmail());
        appService.checkPetSitterExistsByEmail(request.getEmail());
        PetSitter petSitter = petSitterRepository.findPetSitterByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        logger.info("Pet Sitter authenticated successfully with email: {}", petSitter.getEmail());
        return petSitter;
    }

    public Customer signUpCustomer(RegisterCustomerDto request){
        logger.info("Customer registration attempt for email: {}", request.getEmail());
        appService.isCustomerExistsByEmail(request.getEmail());

        Customer customer = new Customer();
        customer.setAge(request.getAge());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setTckNo(request.getTckNo());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setRoles(Roles.CUSTOMER);

        logger.info("Customer registered successfully with email: {}", customer.getEmail());
        return customerRepository.save(customer);

    }

    public Customer authenticateCustomer(LoginDto request){
        logger.info("Customer authentication attempt for email: {}", request.getEmail());
        appService.checkCustomerExistsByEmail(request.getEmail());
        Customer customer = customerRepository.findCustomerByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        logger.info("Customer authenticated successfully with email: {}", customer.getEmail());
        return customer;
    }

    public CorporateCustomer signUpCorporateCustomer(RegisterCorporateCustomerDto request){
        logger.info("Corporate Customer registration attempt for email: {}", request.getEmail());
        appService.isCorporateCustomerExistsByEmail(request.getEmail());

        CorporateCustomer corporateCustomer = new CorporateCustomer();
        corporateCustomer.setEmail(request.getEmail());
        corporateCustomer.setPassword(passwordEncoder.encode(request.getPassword()));
        corporateCustomer.setAddress(request.getAddress());
        corporateCustomer.setTaxNumber(request.getTaxNumber());
        corporateCustomer.setRoles(Roles.CORPORATE_CUSTOMER);

        logger.info("Corporate Customer registered successfully with email: {}", corporateCustomer.getEmail());
        return corporateCustomer;
    }

    public CorporateCustomer authenticateCC(LoginDto request){
        logger.info("Corporate Customer authentication attempt for email: {}", request.getEmail());
        appService.checkCorporateCustomerExistsByEmail(request.getEmail());
        CorporateCustomer corporateCustomer =
                corporateCustomerRepository.findCorporateCustomerByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()));
        logger.info("Corporate Customer authenticated successfully with email: {}", corporateCustomer.getEmail());
        return corporateCustomer;
    }






}