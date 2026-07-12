package org.example.weatherforecast.service;

import org.example.weatherforecast.client.GeoService;
import org.example.weatherforecast.domain.Favorite.Favorite;
import org.example.weatherforecast.domain.Favorite.FavoriteDTO;
import org.example.weatherforecast.domain.Favorite.FavoriteRequestDTO;
import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.domain.user.UserRole;
import org.example.weatherforecast.model.open_meteo_api.ResultsB;
import org.example.weatherforecast.repositories.FavoriteRepository;
import org.hibernate.validator.constraints.CodePointLength;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceTest {
    @Mock
    private GeoService geoService;

    @Mock
    private FavoriteRepository favoriteRepository;

    User user = new User("aure", "teste@teste.com", "12345678", UserRole.USER);

    @Test
    void addFavorite() throws IOException {


        SecurityContext mockedContext = Mockito.mock(SecurityContext.class);
        Authentication mockedAuth = Mockito.mock(Authentication.class);

        ResultsB resultsB = new ResultsB("teste", 0.0000, 0.0000 );

        when(geoService.getCoordinates("teste")).thenReturn(resultsB);
        when(mockedContext.getAuthentication()).thenReturn(mockedAuth);
        when(mockedAuth.getPrincipal()).thenReturn(user);

        FavoriteRequestDTO favoriteRequestDTO = new FavoriteRequestDTO("teste");

        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {


            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockedContext);
            FavoriteService favoriteService = new FavoriteService(geoService, favoriteRepository);
            favoriteService.addFavorite(favoriteRequestDTO);
            Mockito.verify(favoriteRepository).save(Mockito.any(Favorite.class));
        }

    }

    @Test
    void removeFavorite() throws IOException {
        SecurityContext mockedContext = Mockito.mock(SecurityContext.class);
        Authentication mockedAuth = Mockito.mock(Authentication.class);

        ResultsB resultsB = new ResultsB("Medeiros Neto", 0.0000, 0.0000 );

        Favorite favorite = new Favorite(user, "Medeiros Neto");

        when(geoService.getCoordinates(favorite.getCityName())).thenReturn(resultsB);
        when(favoriteRepository.findFavoriteByUserIdAndCityName(user, favorite.getCityName()))
                .thenReturn(Optional.of(favorite));
        when(mockedContext.getAuthentication()).thenReturn(mockedAuth);
        when(mockedAuth.getPrincipal()).thenReturn(user);

        try (MockedStatic<SecurityContextHolder> mockedStatic= Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockedContext);
            FavoriteService favoriteService = new FavoriteService(geoService, favoriteRepository);
            FavoriteRequestDTO favoriteRequestDTO = new FavoriteRequestDTO("Medeiros Neto");
            favoriteService.removeFavorite(favoriteRequestDTO);
            Mockito.verify(favoriteRepository).delete(favorite);
        }
    }

    @Test
    void getFavorite() {
        SecurityContext mockedContext = Mockito.mock(SecurityContext.class);
        Authentication mockedAuth = Mockito.mock(Authentication.class);

        Favorite favorite = new Favorite(user, "Medeiros Neto");

        when(favoriteRepository.findByUserId(user))
                .thenReturn(Collections
                        .singletonList(favorite));
        when(mockedContext.getAuthentication()).thenReturn(mockedAuth);
        when(mockedAuth.getPrincipal()).thenReturn(user);

        try (MockedStatic<SecurityContextHolder> mockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockedContext);
            FavoriteService favoriteService = new FavoriteService(geoService, favoriteRepository);
            ResponseEntity<List<FavoriteDTO>> favoriteEntityList = favoriteService.getFavorite();
            assertEquals(200, favoriteEntityList.getStatusCode().value());
            List<FavoriteDTO> favoriteDTOList = favoriteEntityList.getBody();
            assert favoriteDTOList != null;
            String favoriteDTO = favoriteDTOList.get(0).cityName();
            assertEquals(favorite.getCityName(), favoriteDTO);


        }
    }
}