package org.example.weatherforecast.service;

import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.model.WeatherResponse;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;

import java.io.IOException;

public class WeatherService {

    public WeatherResponse getPrevisao(String city) throws IOException {
            MeteoService meteoService = new MeteoService();
            ResultsWeather weather = meteoService.searchWeather(city);
            return new WeatherResponse(city, weather);
    }


}
