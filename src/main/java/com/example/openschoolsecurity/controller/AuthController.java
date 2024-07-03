package com.example.openschoolsecurity.controller;

import com.example.openschoolsecurity.model.dto.JwtResponse;
import com.example.openschoolsecurity.model.dto.SignInRequest;
import com.example.openschoolsecurity.model.dto.SignUpRequest;
import com.example.openschoolsecurity.service.AuthenticationService;
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
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest request) {
        authenticationService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@RequestBody @Valid SignInRequest request) {
        JwtResponse response = authenticationService.signIn(request);
        return ResponseEntity.ok(response);
    }

}