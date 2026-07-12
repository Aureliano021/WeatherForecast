package org.example.weatherforecast.repositories;

import org.example.weatherforecast.domain.Favorite.Favorite;
import org.example.weatherforecast.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(User userId);
    Optional<Favorite> findFavoriteByUserIdAndCityName(User userId, String cityName);
}