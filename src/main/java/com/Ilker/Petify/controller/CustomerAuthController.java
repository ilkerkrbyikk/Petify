package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.LoginDto;
import com.Ilker.Petify.dto.RegisterCustomerDto;
import com.Ilker.Petify.entity.Customer;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.response.LoginResponse;
import com.Ilker.Petify.service.AuthService;
import com.Ilker.Petify.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/customer")
@RequiredArgsConstructor
public class CustomerAuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUpCustomer(@RequestBody RegisterCustomerDto dto){
        Customer customer = authService.signUpCustomer(dto);
        return ResponseEntity.ok(new ApiResponse("Success.", customer));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginDto dto){
        Customer authenticatedCustomer = authService.authenticateCustomer(dto);
        String jwtToken = jwtService.generateToken(authenticatedCustomer);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
