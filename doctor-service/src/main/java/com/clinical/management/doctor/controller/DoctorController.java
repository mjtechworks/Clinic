package com.clinical.management.doctor.controller;

import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.Weather;
import com.clinical.management.doctor.service.DoctorService;
import com.clinical.management.doctor.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class DoctorController {
    private DoctorService doctorService;
    private WeatherService weatherService;

    @Autowired
    public DoctorController(DoctorService doctorService, WeatherService weatherService) {
        this.doctorService = doctorService;
        this.weatherService = weatherService;
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public Doctor getDoctorByName(@RequestParam(value = "email") String email) {
        return doctorService.findByEmail(email);
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public Doctor getCurrentAccount(Principal principal) {
        return doctorService.findByEmail(principal.getName());
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Doctor createNewDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorService.create(doctor);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public Doctor updateDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public List<Weather> getWeathers() {
        return weatherService.getWeather();
    }

}
