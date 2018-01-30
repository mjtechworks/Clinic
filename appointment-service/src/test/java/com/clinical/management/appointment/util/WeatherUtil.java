package com.clinical.management.appointment.util;

import com.clinical.management.appointment.domain.Weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherUtil {

    public static List<Weather> getWeathers() {
        List<Weather> weathers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            weathers.add(generateWeather());
        }

        return weathers;
    }

    private static Weather generateWeather() {
        Weather weather = new Weather();
        weather.setTempMin(0.0);
        weather.setTempMax(0.0);
        weather.setCountry("RO");
        weather.setCity("Bucharest");
        weather.setDate(new Date());

        return weather;
    }

}
