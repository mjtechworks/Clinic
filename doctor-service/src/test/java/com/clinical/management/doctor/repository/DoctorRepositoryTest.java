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
    public void findDoctorById() {
        Doctor doctor = new Doctor();

        doctor.setId("55");
        doctor.setFullName("Test");
        doctor.setEmail("test@test.com");

        doctorRepository.save(doctor);

        Doctor found = doctorRepository.findOne(doctor.getId());

        assertEquals(doctor.getFullName(), found.getFullName());
        assertEquals(doctor.getEmail(), found.getEmail());
    }

}
