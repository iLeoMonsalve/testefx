package com.leonel.monsalve.equifaxinterview.domain.service.impl;

import com.leonel.monsalve.equifaxinterview.data.entity.User;
import com.leonel.monsalve.equifaxinterview.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        String username = "testUser";
        String rawPassword = "password123";
        String encodedPassword = "encodedPassword123";

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        Boolean result = userService.login(username, rawPassword);

        assertTrue(result);
        verify(userRepository).findByUsername(username);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
    }

    @Test
    void testLoginFailureIncorrectPassword() {
        String username = "testUser";
        String rawPassword = "wrongPassword";
        String encodedPassword = "encodedPassword123";

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        Boolean result = userService.login(username, rawPassword);

        assertFalse(result);
        verify(userRepository).findByUsername(username);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
    }

    @Test
    void testLoginFailureUserNotFound() {
        String username = "nonExistingUser";
        String rawPassword = "password123";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Boolean result = userService.login(username, rawPassword);

        assertFalse(result);
        verify(userRepository).findByUsername(username);
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void testRegister() {
        String rawPassword = "password123";
        String encodedPassword = "encodedPassword123";

        User user = new User();
        user.setUsername("newUser");
        user.setPassword(rawPassword);

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        userService.register(user);

        assertEquals(encodedPassword, user.getPassword());
        verify(passwordEncoder).encode(rawPassword);
        verify(userRepository).save(user);
    }
}