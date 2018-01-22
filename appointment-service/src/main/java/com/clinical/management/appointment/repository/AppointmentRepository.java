package com.clinical.management.appointment.repository;

import com.clinical.management.appointment.domain.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    List<Appointment> findAllByDoctorEmail(String doctorEmail);

    List<Appointment> findAllByDoctorEmailAndPatientId(String doctorEmail, String patientId);

}
