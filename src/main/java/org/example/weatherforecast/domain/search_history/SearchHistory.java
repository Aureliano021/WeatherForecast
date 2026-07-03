package org.example.weatherforecast.domain.search_history;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.weatherforecast.domain.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "search_history")
@Entity(name = "SearchHistory")
@Getter
@NoArgsConstructor
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String city;
    private double temperature;
    private float rain;
    private float rainProbability;

    @CreationTimestamp
    private LocalDateTime searchedAt;



    public SearchHistory(User user, String city, double temperature, float rain, float rainProbability) {
        this.user = user;
        this.city = city;
        this.temperature = temperature;
        this.rain = rain;
        this.rainProbability = rainProbability;
    }
}