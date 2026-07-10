package org.example.weatherforecast.service;

import lombok.RequiredArgsConstructor;
import org.example.weatherforecast.client.GeoService;
import org.example.weatherforecast.domain.Favorite.Favorite;
import org.example.weatherforecast.domain.Favorite.FavoriteDTO;
import org.example.weatherforecast.domain.Favorite.FavoriteRequestDTO;
import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.model.open_meteo_api.ResultsB;
import org.example.weatherforecast.repositories.FavoriteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final GeoService geoService;

    public void addFavorite(FavoriteRequestDTO cityName) throws IOException {
        User user = ((User) Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication())
                .getPrincipal()
        );

        ResultsB resultsB = geoService.getCoordinates(cityName.cityName());
        String cityNameFormatted = resultsB.getName();

        Favorite favorite = new Favorite(user, cityNameFormatted);
        favoriteRepository.save(favorite);

    }

    public void removeFavorite(FavoriteRequestDTO cityName) throws IOException {
        User user = ((User) Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication())
                .getPrincipal()
        );

        String cityNameFormatted = geoService.getCoordinates(
                cityName.cityName()
        ).getName();

        Favorite favorite = new Favorite(user, cityNameFormatted);

        favoriteRepository.delete(favorite);

    }

    private final FavoriteRepository favoriteRepository;

    public ResponseEntity<?> getFavorite() {
        User user = ((User) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal()
        );

        List<Favorite> searchFavorite = favoriteRepository.findByUserId(user);

        List<FavoriteDTO> response = searchFavorite.stream().map(data -> new FavoriteDTO(
                data.getId(),
                data.getCityName(),
                data.getCreatedAt()
        )).toList();

        return ResponseEntity.ok(response);
    }
}
