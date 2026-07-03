package org.example.weatherforecast.service;

import lombok.RequiredArgsConstructor;
import org.example.weatherforecast.domain.search_history.SearchHistory;
import org.example.weatherforecast.domain.search_history.SearchHistoryDTO;
import org.example.weatherforecast.domain.user.User;
import org.example.weatherforecast.repositories.SearchHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;

    public ResponseEntity<?> getHistory() {
        User user = ((User) Objects.requireNonNull(SecurityContextHolder
                .getContext()
                .getAuthentication())
                .getPrincipal());


        List<SearchHistory> searchHistory = searchHistoryRepository.findByUser(user);

        List<SearchHistoryDTO> response = searchHistory.stream()
                .map(history -> new SearchHistoryDTO(
                        history.getId(),
                        history.getCity(),
                        history.getTemperature(),
                        history.getRain(),
                        history.getRainProbability(),
                        history.getSearchedAt()
                ))
                .toList();
        return ResponseEntity.ok(response);

    }


}
