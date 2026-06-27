package org.example.weatherforecast.service;

import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.model.WeatherResponse;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
    private final MeteoService meteoService;

    public WeatherService(MeteoService meteoService) {
        this.meteoService = meteoService;
    }

    public WeatherResponse getPrevisao(String city) throws IOException {
        ResultsWeather weather = meteoService.searchWeather(city);
        String cityFormatted = meteoService.getCityFormatted();
        return new WeatherResponse(cityFormatted, weather);
    }
}
