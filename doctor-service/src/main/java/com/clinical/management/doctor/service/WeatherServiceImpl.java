package com.clinical.management.doctor.service;

import com.clinical.management.doctor.client.WeatherServiceClient;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.Weather;
import com.clinical.management.doctor.parser.WeatherParser;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private WeatherServiceClient weatherServiceClient;
    private DoctorRepository doctorRepository;
    private WeatherParser weatherParser;

    public WeatherServiceImpl(WeatherServiceClient weatherServiceClient, DoctorRepository doctorRepository, WeatherParser weatherParser) {
        this.weatherServiceClient = weatherServiceClient;
        this.doctorRepository = doctorRepository;
        this.weatherParser = weatherParser;
    }

    @Override
    public List<Weather> getWeather() {
        String doctorEmail = getCurrentDoctorEmail();
        Doctor doctor = doctorRepository.findDoctorByEmail(doctorEmail);
        JSONObject jsonObject = weatherServiceClient.getWeather(doctor.getLatitude(), doctor.getLatitude());
        return weatherParser.parse(jsonObject);
    }

    private String getCurrentDoctorEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (String) auth.getPrincipal();
    }

}
