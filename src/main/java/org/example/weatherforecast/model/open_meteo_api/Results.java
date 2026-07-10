package org.example.weatherforecast.model.open_meteo_api;

import lombok.Getter;

import java.util.List;

@Getter
public class Results {
    private List<ResultsB> results;
}
