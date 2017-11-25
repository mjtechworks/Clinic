package com.clinical.management.doctor.controller;

import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Doctor create(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{doctorId}")
    public Doctor get(@PathVariable String doctorId) {
        return doctorRepository.findOne(doctorId);
    }
}
