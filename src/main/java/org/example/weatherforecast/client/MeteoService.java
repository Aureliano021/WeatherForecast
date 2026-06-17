package org.example.weatherforecast.client;

import com.google.gson.Gson;
import org.example.weatherforecast.model.open_meteo_api.ResultsB;
import org.example.weatherforecast.model.open_meteo_api.ResultsWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class MeteoService {
    public ResultsWeather searchWeather(String city) throws IOException {
        HttpURLConnection connection = getHttpURLConnection(city);
        if  (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + connection.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String json = br.lines().collect(Collectors.joining(System.lineSeparator()));
        Gson gson = new Gson();

        return gson.fromJson(json, ResultsWeather.class);

    }

    private static HttpURLConnection getHttpURLConnection(String city) throws IOException {
        GeoService geoService = new GeoService();
        ResultsB resultsB = geoService.getCoordinates(city);
        double latitude = resultsB.getLatitude();
        double longitude = resultsB.getLongitude();
        String http = "https://api.open-meteo.com/v1/forecast?latitude="+ latitude +"&longitude="+ longitude +"&hourly=precipitation_probability,temperature_2m&models=best_match&current=temperature_2m,rain&timezone=America%2FSao_Paulo&forecast_days=1";
        URL url = new URL(http);
        return (HttpURLConnection) url.openConnection();
    }
}
