package org.example.weatherforecast.client;

import com.google.gson.Gson;
import org.example.weatherforecast.exeptions.CityNotFoundException;
import org.example.weatherforecast.model.open_meteo_api.Results;
import org.example.weatherforecast.model.open_meteo_api.ResultsB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class GeoService {

    public ResultsB getCoordinates(String city) throws IOException {
        String correctCity = city.replace(" ", "+");
        String http = "https://geocoding-api.open-meteo.com/v1/search?name="+ correctCity +"&count=1&language=pt&format=json";
        URL url = new URL(http);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String jsonString = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        Gson gson = new Gson();
        Results results = gson.fromJson(jsonString, Results.class);

        if (results.getResults() == null || results.getResults().isEmpty()) {
            throw new CityNotFoundException("No cities found");
        } else {
            return results.getResults().get(0);
        }
    }

}
