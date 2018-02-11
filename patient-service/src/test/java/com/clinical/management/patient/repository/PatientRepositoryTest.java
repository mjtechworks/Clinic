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
        comparePatients(patient, found);
    }

    @Test
    public void findAllByDoctorEmail() throws ParseException {
        List<Patient> patients = PatientUtil.getPatients();
        patientRepository.save(patients);

        List<Patient> founds = patientRepository.findAllByDoctorEmail("doctor@email.com");

        assertEquals(founds.size(), 5);

        for (int i = 0; i < 5; i++) {
            comparePatients(patients.get(i), founds.get(i));
        }
    }


    private void comparePatients(Patient patient1, Patient patient2) {
        assertEquals(patient1.getFirstName(), patient2.getFirstName());
        assertEquals(patient1.getLastName(), patient2.getLastName());
        assertEquals(patient1.getEmail(), patient2.getEmail());
        assertEquals(patient1.getDoctorEmail(), patient2.getDoctorEmail());
        assertEquals(patient1.getPhoneNumber(), patient2.getPhoneNumber());
        assertEquals(patient1.getWeight(), patient2.getWeight());
        assertEquals(patient1.getHeight(), patient2.getHeight());
        assertEquals(patient1.getDateOfBirth(), patient2.getDateOfBirth());
        assertEquals(patient1.getGender(), patient2.getGender());
    }

}
