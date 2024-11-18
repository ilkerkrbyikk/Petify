package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.LoginDto;
import com.Ilker.Petify.dto.RegisterCorporateCustomerDto;
import com.Ilker.Petify.entity.CorporateCustomer;
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
@RequestMapping("/api/auth/c-customer")
@RequiredArgsConstructor
public class CorporateCustomerAuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUpCCustomer(@RequestBody RegisterCorporateCustomerDto dto){
        CorporateCustomer corporateCustomer = authService.signUpCorporateCustomer(dto);
        return ResponseEntity.ok(new ApiResponse("Success.", corporateCustomer));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCCustomer(@RequestBody LoginDto dto){
        CorporateCustomer authenticated = authService.authenticateCC(dto);
        String jwtToken = jwtService.generateToken(authenticated);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}