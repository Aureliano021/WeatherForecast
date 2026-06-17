package org.example.weatherforecast.model;

import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;

public class WeatherResponse {
    private String city;
    private double temperature;
    private double actualRain;
    private double rainProbability;

    public WeatherResponse(String city, ResultsWeather results) {
        this.city = city;
        this.temperature = results.getCurrent().getTemperature_2m();
        this.actualRain = results.getCurrent().getRain();
        this.rainProbability = results.getHourly().getRainProbability().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getActualRain() {
        return actualRain;
    }

    public double getRainProbability() {
        return rainProbability;
    }
}
