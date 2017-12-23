package com.clinical.management.doctor.service;


import com.clinical.management.doctor.DoctorApplication;
import com.clinical.management.doctor.client.AuthServiceClient;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.User;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.print.Doc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DoctorApplication.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Mock
    private DoctorRepository repository;

    @Mock
    private AuthServiceClient authClient;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldFindByEmail() {

        final Doctor doctor = new Doctor();
        doctor.setEmail("test@test.com");

        when(repository.findDoctorByEmail(doctor.getEmail())).thenReturn(doctor);
        Doctor found = doctorService.findByEmail(doctor.getEmail());

        assertEquals(doctor, found);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenEmailIsEmpty() {
        doctorService.findByEmail("");
    }

    @Test
    public void shouldCreateDoctor() {
        Doctor doctor = new Doctor();

        doctor.setFirstName("Test 1");
        doctor.setLastName("Test 2");
        doctor.setEmail("test@test.com");
        doctor.setPassword("testPassword01");
        doctor.setAddress("Test Address");
        doctor.setPhoneNumber("0700000000");

        User user = new User();
        user.setUsername(doctor.getEmail());
        user.setPassword(doctor.getPassword());

        Doctor created = doctorService.create(doctor);
        assertEquals(doctor.getEmail(), created.getEmail());
        assertNull(created.getPassword());

        verify(authClient, times(1)).createUser(user);
        verify(repository, times(1)).save(doctor);
    }

}
