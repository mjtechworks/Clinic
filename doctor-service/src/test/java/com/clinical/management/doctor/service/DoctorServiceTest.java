package com.clinical.management.doctor.service;


import com.clinical.management.doctor.DoctorApplication;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DoctorApplication.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldCreateDoctor() {
        Doctor doctor = new Doctor();

        doctor.setId("55");
        doctor.setFirstName("Test 1");
        doctor.setLastName("Test 2");
        doctor.setEmail("test@test.com");
        doctor.setPassword("testPassword01");
        doctor.setAddress("Test Address");
        doctor.setPhoneNumber("0700000000");

        doctorService.create(doctor);
        verify(repository, times(1)).save(doctor);
    }

}
