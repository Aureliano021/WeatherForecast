package org.example.weatherforecast.model;

import lombok.Getter;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;

@Getter
public class WeatherResponse {
    private final String city;
    private final double temperature;
    private final double actualRain;
    private final float rainProbability;

    public WeatherResponse(String city, ResultsWeather results) {
        this.city = city;
        this.temperature = results.getCurrent().getTemperature_2m();
        this.actualRain = results.getCurrent().getRain();
        this.rainProbability = (float) Math.round(results.getHourly().getRainProbability().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0) * 100.0) / 100.0f;
    }

}
