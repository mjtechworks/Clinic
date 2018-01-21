package com.clinical.management.doctor.util;

import com.clinical.management.doctor.domain.Doctor;

public class DoctorUtil {

    public static Doctor getDoctor() {
        Doctor doctor = new Doctor();

        doctor.setFirstName("Test 1");
        doctor.setLastName("Test 2");
        doctor.setEmail("test@test.com");
        doctor.setPassword("testPassword01");
        doctor.setLatitude(0.0);
        doctor.setLongitude(0.0);
        doctor.setPhoneNumber("0700000000");

        return doctor;
    }


}
