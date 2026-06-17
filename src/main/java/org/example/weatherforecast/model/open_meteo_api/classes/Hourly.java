package org.example.weatherforecast.model.open_meteo_api.classes;

import java.util.List;

public class Hourly {
    @com.google.gson.annotations.SerializedName("precipitation_probability")
    private List<Integer> rainProbability;

    public List<Integer> getRainProbability() {
        return rainProbability;
    }

    @com.google.gson.annotations.SerializedName("temperature_2m")
    private List<Double> temperature_2m;

    public List<Double> getTemperature_2m() {
        return temperature_2m;
    }

    @com.google.gson.annotations.SerializedName("time")
    public List<String> time;

    public List<String> getTime() {
        return time;
    }
}
