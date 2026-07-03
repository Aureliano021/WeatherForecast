package org.example.weatherforecast.domain.Favorite;

import jakarta.validation.constraints.NotBlank;

public record FavoriteRequestDTO(
        @NotBlank String cityName
) {
}
