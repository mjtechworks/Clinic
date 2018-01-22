package com.clinical.management.appointment.controller;


import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.repository.AppointmentRepository;
import com.clinical.management.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    private AppointmentRepository appointmentRepository;
    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository, AppointmentService appointmentService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Appointment> getAllAppointments(@RequestParam(value = "doctorEmail") String doctorEmail, @RequestParam(value = "patientId", required = false) String patientId) {
        return appointmentService.findAllAppointments(doctorEmail, patientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public Appointment update(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{appointmentId}")
    public Appointment get(@PathVariable String appointmentId) {
        return appointmentRepository.findOne(appointmentId);
    }

}
