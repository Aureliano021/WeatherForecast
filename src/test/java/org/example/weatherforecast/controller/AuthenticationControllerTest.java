package org.example.weatherforecast.controller;

import lombok.AllArgsConstructor;
import org.example.weatherforecast.domain.user.RegisterDTO;
import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.domain.user.UserRole;
import org.example.weatherforecast.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@AllArgsConstructor
class AuthenticationControllerTest {


    private UserRepository userRepository;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Test
    void login() {

    }

    @Test
    @Transactional
    void registerOk() throws Exception {
        RegisterDTO user = new RegisterDTO("aure", "teste@teste.com", "12345678", UserRole.USER);
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void registerError() throws Exception {
        User user = new User("aure", "teste@teste.com", "12345678", UserRole.USER);
        userRepository.save(user);
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }
}