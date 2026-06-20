package org.example.weatherforecast.controller;

import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.model.WeatherResponse;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;
import org.example.weatherforecast.service.WeatherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MeteoController {
    @GetMapping("/weather/raw")
    public ResultsWeather getRawWeather(@RequestParam String city) throws IOException {
        MeteoService meteoService = new MeteoService();
        return meteoService.searchWeather(city);
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam String city) throws IOException {
        WeatherService weatherService = new WeatherService();

        return weatherService.getPrevisao(city);
    }
}
