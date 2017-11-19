package com.clinical.management.patient.repository;

import com.clinical.management.patient.PatientApplication;
import com.clinical.management.patient.domain.Patient;
import com.clinical.management.patient.util.PatientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatientApplication.class)
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void findPatientById() throws ParseException {
        Patient patient = PatientUtil.getPatient();

        patientRepository.save(patient);

        Patient found = patientRepository.findOne(patient.getId());

        assertEquals(patient.getFirstName(), found.getFirstName());
        assertEquals(patient.getLastName(), found.getLastName());
        assertEquals(patient.getEmail(), found.getEmail());
        assertEquals(patient.getPhoneNumber(), found.getPhoneNumber());
        assertEquals(patient.getWeight(), found.getWeight());
        assertEquals(patient.getHeight(), found.getHeight());
        assertEquals(patient.getDateOfBirth(), found.getDateOfBirth());
        assertEquals(patient.getGender(), found.getGender());
    }

    @Test
    public void findAllPatients() throws ParseException {
        List<Patient> patients = PatientUtil.getPatients();

        patientRepository.save(patients);

        List<Patient> founds = patientRepository.findAll();

        assertEquals(founds.size(), 5);

        for (int i = 0; i < 5; i++) {
            Patient patient = patients.get(i);
            Patient found = founds.get(i);

            assertEquals(patient.getFirstName(), found.getFirstName());
            assertEquals(patient.getLastName(), found.getLastName());
            assertEquals(patient.getEmail(), found.getEmail());
            assertEquals(patient.getPhoneNumber(), found.getPhoneNumber());
            assertEquals(patient.getWeight(), found.getWeight());
            assertEquals(patient.getHeight(), found.getHeight());
            assertEquals(patient.getDateOfBirth(), found.getDateOfBirth());
            assertEquals(patient.getGender(), found.getGender());
        }
    }


}
