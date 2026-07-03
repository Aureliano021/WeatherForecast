package org.example.weatherforecast.repositories;

import org.example.weatherforecast.domain.search_history.SearchHistory;
import org.example.weatherforecast.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findByUser(User user);
}
