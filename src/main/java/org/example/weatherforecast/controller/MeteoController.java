package org.example.weatherforecast.controller;

import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.model.WeatherResponse;
import org.example.weatherforecast.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MeteoController {

    private WeatherService weatherService;
    private MeteoService meteoService;

    public MeteoController(WeatherService weatherService , MeteoService meteoService) {
        this.weatherService = weatherService;
        this.meteoService = meteoService;
    }

    @GetMapping("/weather/raw")
    public ResponseEntity<?> getRawWeather(@RequestParam String city) throws IOException {
        return ResponseEntity.ok( meteoService.searchWeather(city));
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam String city) throws IOException {
        return ResponseEntity.ok(weatherService.getPrevisao(city));
    }
}
