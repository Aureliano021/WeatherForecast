package org.example.weatherforecast.model.open_meteo_api.classes;

public class HourlyUnits {
    private String time;
    private String precipitation_probability;
    private String temperature_2m;

    public String getPrecipitation_probability() {
        return precipitation_probability;
    }

    public String getTemperature_2m() {
        return temperature_2m;
    }

    public String getTime() {
        return time;
    }
}
