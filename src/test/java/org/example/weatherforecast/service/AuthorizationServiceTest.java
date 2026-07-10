package org.example.weatherforecast.service;

import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.domain.user.UserRole;
import org.example.weatherforecast.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorizationService authorizationService;



    @Test
    void findByEmailTest1() {
        User user = new User("aure", "teste@teste.com", "12345678", UserRole.USER);

        when(userRepository.findByEmail("teste@teste.com")).thenReturn(user);
        UserDetails userDetails = authorizationService.loadUserByUsername("teste@teste.com");
        assertEquals(user, userDetails);


    }


    @Test
    void testError() {
        when(userRepository.findByEmail("teste@teste.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> authorizationService.loadUserByUsername("teste@teste.com"));

    }
}