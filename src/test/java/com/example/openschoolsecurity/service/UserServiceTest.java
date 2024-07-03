package com.example.openschoolsecurity.service;

import com.example.openschoolsecurity.config.MyUserDetails;
import com.example.openschoolsecurity.model.User;
import com.example.openschoolsecurity.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.save(user);
        assertEquals(user, savedUser);
    }

    @Test
    void create() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@example.com");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.create(user);
        assertEquals(user, createdUser);
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setUsername("username");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        MyUserDetails userDetails = (MyUserDetails) userService.userDetailsService().loadUserByUsername("username");
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test
    void loadUserByUsernameNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.userDetailsService().loadUserByUsername("username");
        });
    }
}