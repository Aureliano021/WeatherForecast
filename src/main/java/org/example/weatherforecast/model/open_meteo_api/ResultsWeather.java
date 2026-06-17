package org.example.weatherforecast.model.open_meteo_api;

import com.google.gson.annotations.SerializedName;
import org.example.weatherforecast.model.open_meteo_api.classes.Current;
import org.example.weatherforecast.model.open_meteo_api.classes.CurrentUnits;
import org.example.weatherforecast.model.open_meteo_api.classes.Hourly;
import org.example.weatherforecast.model.open_meteo_api.classes.HourlyUnits;

public class ResultsWeather {
    private double latitude;
    private double longitude;
    private String generationtime_ms;
    private double utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private Current current;
    private Hourly hourly;

    @SerializedName("current_units")
    private CurrentUnits currentUnits;

    @SerializedName("hourly_units")
    private HourlyUnits hourlyUnits;

    public double getElevation() {
        return elevation;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public String getTimezone() {
        return timezone;
    }

    public double getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public String getGenerationtime_ms() {
        return generationtime_ms;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }

    public CurrentUnits getCurrentUnits() {
        return currentUnits;
    }

    public Current getCurrent() {
        return current;
    }

    public Hourly getHourly() {
        return hourly;
    }
}
