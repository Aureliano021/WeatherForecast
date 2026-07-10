package org.example.weatherforecast.infra;

import org.example.weatherforecast.exeptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CityNotFoundException.class})
    public ResponseEntity<?> cityNotFoundHandler() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not Found");
    }
}
