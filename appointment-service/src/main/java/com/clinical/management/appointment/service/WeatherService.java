package com.clinical.management.appointment.service;

import com.clinical.management.appointment.domain.Weather;

import java.text.ParseException;
import java.util.List;

public interface WeatherService {
    List<Weather> getWeather(Double lat, Double lon) throws ParseException;
}
