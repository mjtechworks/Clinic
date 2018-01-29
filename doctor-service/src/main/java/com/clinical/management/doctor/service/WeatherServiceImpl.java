package com.clinical.management.doctor.service;

import com.clinical.management.doctor.client.WeatherServiceClient;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.Weather;
import com.clinical.management.doctor.parser.WeatherParse;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private WeatherServiceClient weatherServiceClient;
    private DoctorRepository doctorRepository;
    private WeatherParse weatherParser;

    @Value("${weather.id}")
    private Integer id;

    @Value("${weather.APPID}")
    private String appid;

    public WeatherServiceImpl(WeatherServiceClient weatherServiceClient, DoctorRepository doctorRepository, WeatherParse weatherParser) {
        this.weatherServiceClient = weatherServiceClient;
        this.doctorRepository = doctorRepository;
        this.weatherParser = weatherParser;
    }

    @Override
    public List<Weather> getWeather(String doctorEmail) throws JSONException, ParseException {
        Doctor doctor = doctorRepository.findDoctorByEmail(doctorEmail);
        JSONObject jsonObject = weatherServiceClient.getWeather(id, appid, doctor.getLatitude(), doctor.getLatitude());
        return weatherParser.parse(jsonObject);
    }

}
