package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.LoginDto;
import com.Ilker.Petify.dto.RegisterAdminDto;
import com.Ilker.Petify.entity.Admin;
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
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Auth")
public class AdminAuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(
            summary = "Sign up request for admins. Allows a new admin user to register. Creates an admin account based on the provided details."
    )
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUpAdmin(@RequestBody RegisterAdminDto dto){
        Admin admin = authService.signUpAdmin(dto);
        return ResponseEntity.ok(new ApiResponse("Success.", admin));
    }

    @Operation(
            summary = "Login request for admins. Allows an admin user to log in. Generates and returns a JWT token upon successful authentication."

    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginDto dto){
        Admin authenticatedAdmin = authService.authenticateAdmin(dto);
        String jwtToken = jwtService.generateToken(authenticatedAdmin);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
