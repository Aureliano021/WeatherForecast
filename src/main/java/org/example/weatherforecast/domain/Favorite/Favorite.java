package org.example.weatherforecast.domain.Favorite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.weatherforecast.domain.user.User;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;



@Table(name = "favorite_cities")
@Entity(name = "Favorite")
@Getter
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    private String cityName;

    @CurrentTimestamp
    private LocalDateTime createdAt;

    public Favorite(User user, String cityName) {
        this.userId = user;
        this.cityName = cityName;
    }
}
