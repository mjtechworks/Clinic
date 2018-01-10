package com.clinical.management.patient.repository;

import com.clinical.management.patient.domain.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findAllByDoctorEmail(String doctorEmail);

}
