package org.example.weatherforecast.model;

import org.example.weatherforecast.client.GeoService;
import org.example.weatherforecast.client.MeteoService;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;

import java.io.IOException;

public class WeatherResponse {
    private String city;
    private double temperature;
    private double actualRain;
    private float rainProbability;

    public WeatherResponse(String city, ResultsWeather results) throws IOException {
        this.city = new GeoService().getCoordinates(city).getName();
        this.temperature = results.getCurrent().getTemperature_2m();
        this.actualRain = results.getCurrent().getRain();
        this.rainProbability = (float) Math.round(results.getHourly().getRainProbability().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0) * 100.0) / 100.0f;
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

    public float getRainProbability() {
        return rainProbability;
    }
}
