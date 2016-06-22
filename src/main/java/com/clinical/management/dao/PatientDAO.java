package com.clinical.management.dao;


import com.clinical.management.model.Patient;

import java.util.List;

public interface PatientDAO {

    Patient getPatientById(int id);

    List<Patient> getPatientsByDoctorID(int doctorID);

    List<Patient> getPatientsByName(int doctorID, String name);

    void addPatient(Patient patient);

    void updatePatient(Patient patient);

    void deletePatientById(int id);

}
