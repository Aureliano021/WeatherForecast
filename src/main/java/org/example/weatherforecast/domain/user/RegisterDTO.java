package org.example.weatherforecast.domain.user;

public record RegisterDTO(String name, String email, String password, UserRole role) {

}
