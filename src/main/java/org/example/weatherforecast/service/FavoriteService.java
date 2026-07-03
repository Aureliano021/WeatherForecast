package org.example.weatherforecast.service;

import lombok.RequiredArgsConstructor;
import org.example.weatherforecast.domain.Favorite.Favorite;
import org.example.weatherforecast.domain.Favorite.FavoriteDTO;
import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.repositories.FavoriteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    public void addFavorite(String cityName) {
        User user = ((User) Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication())
                .getPrincipal()
        );

        Favorite favorite = new Favorite(user, cityName);
        favoriteRepository.save(favorite);

    }

    public void removeFavorite() {
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
