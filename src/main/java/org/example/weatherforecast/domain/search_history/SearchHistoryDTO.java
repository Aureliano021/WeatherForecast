package org.example.weatherforecast.domain.search_history;

import java.time.LocalDateTime;

public record SearchHistoryDTO(Long id, String city, double temperature, float rain, float rainProbability, LocalDateTime searchedAt) {
}
