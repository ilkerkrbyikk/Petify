package com.Ilker.Petify.service;

import com.Ilker.Petify.dto.*;
import com.Ilker.Petify.entity.*;
import com.Ilker.Petify.enums.Roles;
import com.Ilker.Petify.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

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
        appService.isAdminExistsByEmail(request.getEmail());
        Admin admin = new Admin();
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Roles.ADMIN);

        return adminRepository.save(admin);
    }

    public Admin authenticateAdmin(LoginDto request){
        appService.checkAdminExistsByEmail(request.getEmail());
        Admin admin = adminRepository.findByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        return admin;
    }

    public PetSitter signUpPetSitter(RegisterPetSitterDto request){
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

        return petSitterRepository.save(petSitter);
    }

    public PetSitter authenticateSitter(LoginDto request){
        appService.checkPetSitterExistsByEmail(request.getEmail());
        PetSitter petSitter = petSitterRepository.findPetSitterByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        return petSitter;
    }

    public Customer signUpCustomer(RegisterCustomerDto request){
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

        return customerRepository.save(customer);

    }

    public Customer authenticateCustomer(LoginDto request){
        appService.checkCustomerExistsByEmail(request.getEmail());
        Customer customer = customerRepository.findCustomerByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        return customer;
    }

    public CorporateCustomer signUpCorporateCustomer(RegisterCorporateCustomerDto request){
        appService.isCorporateCustomerExistsByEmail(request.getEmail());

        CorporateCustomer corporateCustomer = new CorporateCustomer();
        corporateCustomer.setEmail(request.getEmail());
        corporateCustomer.setPassword(passwordEncoder.encode(request.getPassword()));
        corporateCustomer.setAddress(request.getAddress());
        corporateCustomer.setTaxNumber(request.getTaxNumber());
        corporateCustomer.setRoles(Roles.CORPORATE_CUSTOMER);

        return corporateCustomer;
    }

    public CorporateCustomer authenticateCC(LoginDto request){
        appService.checkCorporateCustomerExistsByEmail(request.getEmail());
        CorporateCustomer corporateCustomer =
                corporateCustomerRepository.findCorporateCustomerByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()));

        return corporateCustomer;
    }






}