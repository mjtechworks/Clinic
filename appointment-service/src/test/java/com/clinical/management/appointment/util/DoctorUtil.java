package com.clinical.management.appointment.util;

import com.clinical.management.appointment.domain.Doctor;

public class DoctorUtil {

    public static Doctor getDoctor() {
        Doctor doctor = new Doctor();
        doctor.setEmail("doctor@test.com");
        doctor.setFirstName("First");
        doctor.setLastName("Last");
        doctor.setPhoneNumber("0777777777");

        return doctor;
    }

}
