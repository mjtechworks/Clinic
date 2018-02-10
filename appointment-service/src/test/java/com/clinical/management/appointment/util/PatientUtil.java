package com.clinical.management.appointment.util;

import com.clinical.management.appointment.domain.Patient;

public class PatientUtil {

    public static Patient getPatient() {
        Patient patient = new Patient();
        patient.setEmail("lungu.daniel94@gmail.com");
        patient.setFirstName("Daniel");
        patient.setLastName("Lungu");

        return patient;
    }

}
