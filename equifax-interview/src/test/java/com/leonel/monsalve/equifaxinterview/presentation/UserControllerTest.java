package com.leonel.monsalve.equifaxinterview.presentation;

import com.leonel.monsalve.equifaxinterview.data.entity.User;
import com.leonel.monsalve.equifaxinterview.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Arrange
        User user = new User();

        // Act
        ResponseEntity<Void> responseEntity = userController.register(user);

        // Assert
        verify(userService).register(any(User.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(URI.create("/api/v1/user"), responseEntity.getHeaders().getLocation());
    }

    @Test
    void testLoginSuccess() {
        // Arrange
        when(userService.login(anyString(), anyString())).thenReturn(true);

        // Act
        ResponseEntity<Boolean> responseEntity = userController.login("testUser", "testPassword");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testLoginFailure() {
        // Arrange
        when(userService.login(anyString(), anyString())).thenReturn(false);

        // Act
        ResponseEntity<Boolean> responseEntity = userController.login("testUser", "testPassword");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}