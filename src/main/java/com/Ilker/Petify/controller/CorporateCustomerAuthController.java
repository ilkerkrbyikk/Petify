package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.LoginDto;
import com.Ilker.Petify.dto.RegisterCorporateCustomerDto;
import com.Ilker.Petify.entity.CorporateCustomer;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.response.LoginResponse;
import com.Ilker.Petify.service.AuthService;
import com.Ilker.Petify.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/c-customer")
@RequiredArgsConstructor
@Tag(name = "Corporate Customer Auth")
public class CorporateCustomerAuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(
            summary = "Registers a new corporate customer."
    )
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUpCCustomer(@RequestBody RegisterCorporateCustomerDto dto){
        CorporateCustomer corporateCustomer = authService.signUpCorporateCustomer(dto);
        return ResponseEntity.ok(new ApiResponse("Success.", corporateCustomer));
    }


    @Operation(
            summary = "Logs in a corporate customer and returns a JWT token."
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCCustomer(@RequestBody LoginDto dto){
        CorporateCustomer authenticated = authService.authenticateCC(dto);
        String jwtToken = jwtService.generateToken(authenticated);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}