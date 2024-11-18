package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.LoginDto;
import com.Ilker.Petify.dto.RegisterPetSitterDto;
import com.Ilker.Petify.entity.PetSitter;
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
@RequestMapping("/api/auth/sitter")
@RequiredArgsConstructor
public class PetSitterAuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUpSitter(@RequestBody RegisterPetSitterDto dto){
        PetSitter petSitter = authService.signUpPetSitter(dto);
        return ResponseEntity.ok(new ApiResponse("Success.", petSitter));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginDto dto){
        PetSitter authenticatedSitter = authService.authenticateSitter(dto);
        String jwtToken = jwtService.generateToken(authenticatedSitter);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
