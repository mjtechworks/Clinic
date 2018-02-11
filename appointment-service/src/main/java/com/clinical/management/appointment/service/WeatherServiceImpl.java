package com.clinical.management.appointment.service;

import com.clinical.management.appointment.client.WeatherServiceClient;
import com.clinical.management.appointment.domain.Weather;
import com.clinical.management.appointment.component.WeatherParse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private WeatherServiceClient weatherServiceClient;
    private WeatherParse weatherParser;

    @Value("${weather.id}")
    private Integer id;

    @Value("${weather.APPID}")
    private String appid;

    public WeatherServiceImpl(WeatherServiceClient weatherServiceClient, WeatherParse weatherParser) {
        this.weatherServiceClient = weatherServiceClient;
        this.weatherParser = weatherParser;
    }

    @Override
    public List<Weather> getWeather(Double lat, Double lon) throws ParseException {
        JSONObject jsonObject = weatherServiceClient.getWeather(id, appid, lat, lon);
        return weatherParser.parse(jsonObject);
    }

}
