package org.example.weatherforecast.service;

import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.domain.search_history.SearchHistory;
import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.model.WeatherResponse;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;
import org.example.weatherforecast.repositories.SearchHistoryRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class WeatherService {
    private final MeteoService meteoService;
    private final SearchHistoryRepository searchHistoryRepository;

    public WeatherService(MeteoService meteoService, SearchHistoryRepository searchHistoryRepository) {
        this.meteoService = meteoService;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public WeatherResponse getPrevisao(String city) throws IOException {
        ResultsWeather weather = meteoService.searchWeather(city);
        String cityFormatted = meteoService.getCityFormatted();

        User user = ((User) Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication())
                .getPrincipal());

        WeatherResponse weatherHistory = new WeatherResponse(cityFormatted, weather);
        SearchHistory searchHistory = new SearchHistory(user, cityFormatted
                , weatherHistory.getTemperature()
                , ((float) weatherHistory.getActualRain())
                , weatherHistory.getRainProbability());
        searchHistoryRepository.save(searchHistory);
        return weatherHistory;

    }


}
