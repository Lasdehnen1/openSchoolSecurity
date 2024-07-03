package com.example.openschoolsecurity.service;

import com.example.openschoolsecurity.config.MyUserDetails;
import com.example.openschoolsecurity.model.Role;
import com.example.openschoolsecurity.model.User;
import com.example.openschoolsecurity.model.dto.JwtResponse;
import com.example.openschoolsecurity.model.dto.SignInRequest;
import com.example.openschoolsecurity.model.dto.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUp() {
        SignUpRequest request = new SignUpRequest();
        request.setUsername("testuser");
        request.setEmail("testuser@example.com");
        request.setPassword("password");
        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("encodedPassword")
                .role(Role.ROLE_USER)
                .build();


        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userService.create(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(any(MyUserDetails.class))).thenReturn("jwtToken");

        JwtResponse response = authenticationService.signUp(request);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());

        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(userService, times(1)).create(any(User.class));
        verify(jwtService, times(1)).generateToken(any(MyUserDetails.class));
    }

    @Test
    public void testSignIn() {
        SignInRequest request = new SignInRequest();
        request.setUsername("testuser");
        request.setPassword("password");
        User user = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("encodedPassword")
                .role(Role.ROLE_USER)
                .build();
        MyUserDetails userDetails = new MyUserDetails(user);

        when(userService.userDetailsService()).thenReturn(mock(UserDetailsService.class));
        when(userService.userDetailsService().loadUserByUsername(request.getUsername())).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("jwtToken");

        JwtResponse response = authenticationService.signIn(request);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, times(2)).userDetailsService();
        verify(userService.userDetailsService(), times(1)).loadUserByUsername(request.getUsername());
        verify(jwtService, times(1)).generateToken(userDetails);
    }
}