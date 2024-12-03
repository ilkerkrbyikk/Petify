package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.LoginDto;
import com.Ilker.Petify.dto.RegisterPetSitterDto;
import com.Ilker.Petify.entity.PetSitter;
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
@RequestMapping("/api/auth/sitter")
@RequiredArgsConstructor
@Tag(name = "Pet Sitter Auth")
public class PetSitterAuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(
            summary = "Registers a new pet sitter.",
            description = "This endpoint allows the registration of a new pet sitter. " +
                    "The request must include the necessary details in the request body. " +
                    "Upon successful registration, the newly created pet sitter will be returned."
    )
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUpSitter(@Valid  @RequestBody RegisterPetSitterDto dto){
        PetSitter petSitter = authService.signUpPetSitter(dto);
        return ResponseEntity.ok(new ApiResponse("Success.", petSitter));
    }

    @Operation(
            summary = "Logs in a pet sitter.",
            description = "This endpoint allows a pet sitter to log in. " +
                    "The request must include the login credentials in the request body. " +
                    "Upon successful authentication, a JWT token will be generated and returned along with its expiration time."
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAdmin(@Valid @RequestBody LoginDto dto){
        PetSitter authenticatedSitter = authService.authenticateSitter(dto);
        String jwtToken = jwtService.generateToken(authenticatedSitter);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
