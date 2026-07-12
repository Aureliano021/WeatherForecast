package org.example.weatherforecast.exeptions;

public class FavoriteNotFoundExeption extends RuntimeException{
    public FavoriteNotFoundExeption() {
        super("ERROR: Favorite not found");
    }

    public FavoriteNotFoundExeption(String message) {
        super(message);
    }
}
