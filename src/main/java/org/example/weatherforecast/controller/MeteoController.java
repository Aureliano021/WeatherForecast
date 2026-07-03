package org.example.weatherforecast.controller;

import jakarta.validation.Valid;
import org.example.weatherforecast.client.GeoService;
import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.domain.Favorite.Favorite;
import org.example.weatherforecast.domain.Favorite.FavoriteDTO;
import org.example.weatherforecast.domain.Favorite.FavoriteRequestDTO;
import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.model.open_meteo_api.ResultsB;
import org.example.weatherforecast.repositories.FavoriteRepository;
import org.example.weatherforecast.service.FavoriteService;
import org.example.weatherforecast.service.SearchHistoryService;
import org.example.weatherforecast.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MeteoController {

    private final WeatherService weatherService;
    private final MeteoService meteoService;
    private final SearchHistoryService searchHistoryService;
    private final FavoriteService favoriteService;
    private final FavoriteRepository favoriteRepository;

    public MeteoController(
            WeatherService weatherService,
            MeteoService meteoService,
            SearchHistoryService searchHistoryService,
            FavoriteService favoriteService,
            FavoriteRepository favoriteRepository

            ) {
        this.weatherService = weatherService;
        this.meteoService = meteoService;
        this.searchHistoryService = searchHistoryService;
        this.favoriteService = favoriteService;
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping("/weather/raw")
    public ResponseEntity<?> getRawWeather(@RequestParam String city) throws IOException {
        return ResponseEntity.ok( meteoService.searchWeather(city));
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam String city) throws IOException {
        return ResponseEntity.ok(weatherService.getPrevisao(city));
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory() throws IOException {
        return searchHistoryService.getHistory();
    }

    @GetMapping("/favorite")
    public ResponseEntity<?> getFavorite() throws IOException {
        return favoriteService.getFavorite();
    }

    @PostMapping("/favorite")
    public ResponseEntity<?> PostFavorite(@RequestBody @Valid FavoriteRequestDTO city) throws IOException {
        GeoService geoService = new GeoService();
        ResultsB resultsB = geoService.getCoordinates(city.cityName());
        String cityFormatted = resultsB.getName();

        User user = ((User) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal());
        this.favoriteRepository.save(new Favorite(user, cityFormatted));
        return ResponseEntity.ok().build();
    }
}
