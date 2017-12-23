package com.clinical.management.doctor.controller;

import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(path = "/{email}", method = RequestMethod.GET)
    public Doctor getDoctorByName(@PathVariable String email) {
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

}
