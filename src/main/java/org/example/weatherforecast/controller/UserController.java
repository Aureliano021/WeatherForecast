package org.example.weatherforecast.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.weatherforecast.domain.Favorite.FavoriteRequestDTO;
import org.example.weatherforecast.service.FavoriteService;
import org.example.weatherforecast.service.SearchHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final SearchHistoryService searchHistoryService;
    private final FavoriteService favoriteService;

    @GetMapping("/history")
    public ResponseEntity<?> getHistory() {
        return searchHistoryService.getHistory();
    }

    @GetMapping("/favorite")
    public ResponseEntity<?> getFavorite() {
        return favoriteService.getFavorite();
    }

    @PostMapping("/favorite")
    public ResponseEntity<?> PostFavorite(@RequestBody @Valid FavoriteRequestDTO city) throws IOException {
        favoriteService.addFavorite(city);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favorite")
    public ResponseEntity<?> removeFavorite(@RequestBody @Valid FavoriteRequestDTO city) throws IOException {
        favoriteService.removeFavorite(city);
        return ResponseEntity.ok().build();
    }
}
