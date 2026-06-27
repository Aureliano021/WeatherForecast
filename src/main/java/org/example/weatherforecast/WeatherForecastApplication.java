package org.example.weatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WeatherForecastApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WeatherForecastApplication.class, args);

    }

}
