package com.clinical.management.appointment.service;

import com.clinical.management.appointment.domain.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> findAllAppointments(String doctorEmail, String patientId);

}
