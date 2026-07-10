package org.example.weatherforecast.domain.Favorite;

import java.time.LocalDateTime;

public record FavoriteDTO(
        long id,
        String cityName,
        LocalDateTime createdAt
) {
}
