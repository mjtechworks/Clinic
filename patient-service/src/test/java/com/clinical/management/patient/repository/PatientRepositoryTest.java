package com.clinical.management.patient.repository;

import com.clinical.management.patient.PatientApplication;
import com.clinical.management.patient.domain.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatientApplication.class)
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void findPatientById() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        Patient patient = new Patient();

        patient.setId("55");
        patient.setFirstName("Test 1");
        patient.setLastName("Test 2");
        patient.setEmail("test@test.com");
        patient.setPhoneNumber("0700000000");
        patient.setHeight(175);
        patient.setWeight(80);
        patient.setDateOfBirth(sdf.parse("10/10/1990"));

        patientRepository.save(patient);

        Patient found = patientRepository.findOne(patient.getId());

        assertEquals(patient.getFirstName(), found.getFirstName());
        assertEquals(patient.getLastName(), found.getLastName());
        assertEquals(patient.getEmail(), found.getEmail());
        assertEquals(patient.getPhoneNumber(), found.getPhoneNumber());
        assertEquals(patient.getWeight(), found.getWeight());
        assertEquals(patient.getHeight(), found.getHeight());
        assertEquals(patient.getDateOfBirth(), found.getDateOfBirth());
    }

}
