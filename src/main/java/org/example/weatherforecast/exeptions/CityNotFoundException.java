package org.example.weatherforecast.exeptions;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException() {
        super("City not found");
    }

    public CityNotFoundException(String message) {
        super(message);
    }
}
