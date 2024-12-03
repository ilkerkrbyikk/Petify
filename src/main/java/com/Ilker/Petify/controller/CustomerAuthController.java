package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.LoginDto;
import com.Ilker.Petify.dto.RegisterCustomerDto;
import com.Ilker.Petify.entity.Customer;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.response.LoginResponse;
import com.Ilker.Petify.service.AuthService;
import com.Ilker.Petify.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/customer")
@RequiredArgsConstructor
@Tag(name = "Customer Auth")
public class CustomerAuthController {

    private final AuthService authService;
    private final JwtService jwtService;


    @Operation(
            summary = "Registers a new customer."
    )
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUpCustomer(@Valid @RequestBody RegisterCustomerDto dto){
        Customer customer = authService.signUpCustomer(dto);
        return ResponseEntity.ok(new ApiResponse("Success.", customer));
    }

    @Operation(
            summary = "Logs in a customer and returns a JWT token."
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@Valid @RequestBody LoginDto dto){
        Customer authenticatedCustomer = authService.authenticateCustomer(dto);
        String jwtToken = jwtService.generateToken(authenticatedCustomer);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
