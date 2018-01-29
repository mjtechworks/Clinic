package com.clinical.management.doctor.service;

import com.clinical.management.doctor.domain.Weather;
import org.codehaus.jettison.json.JSONException;

import java.text.ParseException;
import java.util.List;

public interface WeatherService {
    List<Weather> getWeather(String doctorEmail) throws JSONException, ParseException;
}
