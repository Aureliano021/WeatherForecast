package org.example.weatherforecast.model.open_meteo_api.classes;

public class CurrentUnits {
    private String time;
    private String interval;
    private String temperature_2m;
    private String rain;

    public String getTime() {
        return time;
    }

    public String getInterval() {
        return interval;
    }

    public String getTemperature_m2() {
        return temperature_2m;
    }

    public String getRain() {
        return rain;
    }

}
