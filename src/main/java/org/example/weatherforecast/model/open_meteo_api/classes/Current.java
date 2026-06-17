package org.example.weatherforecast.model.open_meteo_api.classes;

import java.time.LocalDateTime;

public class Current {
        private String time;
        private double temperature_2m;
        private double rain;

        public double getRain() {
            return rain;
        }

        public double getTemperature_2m() {
            return temperature_2m;
        }

        public LocalDateTime getTime() {
            if(this.time == null) return null;
            return LocalDateTime.parse(this.time);
        }


}
