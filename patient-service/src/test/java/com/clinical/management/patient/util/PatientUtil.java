package com.clinical.management.patient.util;

import com.clinical.management.patient.domain.Gender;
import com.clinical.management.patient.domain.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PatientUtil {

    public static Patient getPatient() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        Patient patient = new Patient();

        patient.setId("55");
        patient.setDoctorEmail("doctor@email.com");
        patient.setFirstName("Test 1");
        patient.setLastName("Test 2");
        patient.setEmail("test@test.com");
        patient.setPhoneNumber("0700000000");
        patient.setHeight(175);
        patient.setWeight(80);
        patient.setDateOfBirth(sdf.parse("10/10/1990"));
        patient.setGender(Gender.MASCULINE);

        return patient;
    }

    public static List<Patient> getPatients() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        List<Patient> patients = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Patient patient = new Patient();

            patient.setId("55" + i);
            patient.setDoctorEmail("doctor@email.com");
            patient.setFirstName("Test " + i);
            patient.setLastName("Test 2-" + i);
            patient.setEmail("test@test.com");
            patient.setPhoneNumber("070000000" + i);
            patient.setHeight(175 + i);
            patient.setWeight(80 + i);
            patient.setDateOfBirth(sdf.parse("10/10/1990"));
            patient.setGender(Gender.FEMININE);

            patients.add(patient);
        }

        return patients;
    }

}
