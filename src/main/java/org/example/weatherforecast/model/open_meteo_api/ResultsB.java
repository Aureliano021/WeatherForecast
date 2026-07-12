package org.example.weatherforecast.model.open_meteo_api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResultsB {
    private String name;
    private double latitude;
    private double longitude;

}
