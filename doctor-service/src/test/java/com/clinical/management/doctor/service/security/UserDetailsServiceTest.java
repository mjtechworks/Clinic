package com.clinical.management.doctor.service.security;


import com.clinical.management.doctor.DoctorApplication;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.User;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DoctorApplication.class)
public class UserDetailsServiceTest {

    @InjectMocks
    private UserDetailsService service;

    @Mock
    private DoctorRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldLoadByUsernameWhenUserExists() {
        final Doctor doctor = new Doctor();

        when(repository.findDoctorByEmail(any())).thenReturn(doctor);
        UserDetails loaded = service.loadUserByUsername("email");

        User user = new User();
        user.setId(user.getId());
        user.setPassword(doctor.getPassword());
        user.setUsername(doctor.getEmail());

        assertEquals(user, loaded);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldFailToLoadByUsernameWhenUserNotExists() {
        service.loadUserByUsername("email");
    }

}
