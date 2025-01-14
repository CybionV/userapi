package com.vma.userapi.service;

import com.vma.userapi.dto.UserResponseDTO;
import com.vma.userapi.model.User;
import com.vma.userapi.repository.UserRepository;
import com.vma.userapi.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class userServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(jwtTokenUtil.generateToken(anyString())).thenReturn("dummy-jwt-token");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponseDTO responseDTO = userService.createUser(user);

        assertNotNull(responseDTO);
        assertEquals("test@example.com", user.getEmail());
        assertEquals("dummy-jwt-token", responseDTO.getTokenJwt());
        assertNotNull(responseDTO.getCreateDate());
        assertNotNull(responseDTO.getModifiedDate());
        assertEquals(responseDTO.getCreateDate(), responseDTO.getLastLogin());
        assertTrue(responseDTO.getIsActive());
    }

    @Test
    void testCreateUserWithExistingEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(user);
        });

        assertEquals("El correo electrónico ya está registrado.", exception.getMessage());
    }

    @Test
    void testCreateUserWithInvalidEmail() {
        User user = new User();
        user.setEmail("invalid-email");
        user.setPassword("password");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(user);
        });

        assertEquals("El correo electrónico no tiene un formato válido.", exception.getMessage());
    }
}