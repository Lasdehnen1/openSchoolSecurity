package com.example.openschoolsecurity.controller;

import com.example.openschoolsecurity.model.dto.JwtResponse;
import com.example.openschoolsecurity.model.dto.SignInRequest;
import com.example.openschoolsecurity.model.dto.SignUpRequest;
import com.example.openschoolsecurity.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {
    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void signUp() {
//        SignUpRequest request = new SignUpRequest("username", "email@example.com", "password");
//        JwtResponse jwtResponse = new JwtResponse("token");
//        when(authenticationService.signUp(any(SignUpRequest.class))).thenReturn(jwtResponse);
//        ResponseEntity<JwtResponse> response = authController.signUp(request);
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals("token", response.getBody().getToken());
//    }
//
//    @Test
//    void signIn() {
//        SignInRequest request = new SignInRequest("username", "password");
//        JwtResponse jwtResponse = new JwtResponse("token");
//        when(authenticationService.signIn(any(SignInRequest.class))).thenReturn(jwtResponse);
//        ResponseEntity<JwtResponse> response = authController.signIn(request);
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals("token", response.getBody().getToken());
//    }
}
