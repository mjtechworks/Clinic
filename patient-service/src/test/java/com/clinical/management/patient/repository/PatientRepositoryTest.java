package com.clinical.management.patient.repository;

import com.clinical.management.patient.PatientApplication;
import com.clinical.management.patient.domain.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatientApplication.class)
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void findPatientById() {
        Patient patient = new Patient();

        patient.setId("55");
        patient.setEmail("test@test.com");
        patient.setFullName("Test Test");

        patientRepository.save(patient);

        Patient found = patientRepository.findOne(patient.getId());

        assertEquals(patient.getEmail(), found.getEmail());
        assertEquals(patient.getFullName(), found.getFullName());
    }

}
