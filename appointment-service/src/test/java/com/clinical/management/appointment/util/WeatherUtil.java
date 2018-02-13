package com.clinical.management.appointment.util;

import com.clinical.management.appointment.domain.Weather;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    public static JSONObject getWeatherJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        BufferedReader bufferedReader = getFile("jsonWeather.json");

        return (JSONObject) parser.parse(bufferedReader);
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

    private static BufferedReader getFile(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        return new BufferedReader(streamReader);
    }

}
