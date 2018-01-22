package com.clinical.management.appointment.service;

import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository repository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Appointment> findAllAppointments(String doctorEmail, String patientId) {
        if (patientId == null || patientId.isEmpty()) {
            return repository.findAllByDoctorEmail(doctorEmail);
        } else {
            return repository.findAllByDoctorEmailAndPatientId(doctorEmail, patientId);
        }
    }
}
