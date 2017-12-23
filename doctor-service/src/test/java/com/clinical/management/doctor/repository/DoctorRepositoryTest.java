package com.clinical.management.doctor.repository;


import com.clinical.management.doctor.DoctorApplication;
import com.clinical.management.doctor.domain.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DoctorApplication.class)
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void findDoctorByEmail() {
        Doctor doctor = new Doctor();

        doctor.setFirstName("Test 1");
        doctor.setLastName("Test 2");
        doctor.setEmail("test@test.com");
        doctor.setAddress("Test Address");
        doctor.setPhoneNumber("0700000000");

        doctorRepository.save(doctor);

        Doctor found = doctorRepository.findDoctorByEmail(doctor.getEmail());

        assertEquals(doctor.getFirstName(), found.getFirstName());
        assertEquals(doctor.getLastName(), found.getLastName());
        assertEquals(doctor.getEmail(), found.getEmail());
        assertEquals(doctor.getAddress(), found.getAddress());
        assertEquals(doctor.getPhoneNumber(), found.getPhoneNumber());
    }

}
