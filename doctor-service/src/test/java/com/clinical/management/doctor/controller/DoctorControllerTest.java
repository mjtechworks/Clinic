package com.clinical.management.doctor.controller;


import com.clinical.management.doctor.DoctorApplication;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DoctorApplication.class)
@WebAppConfiguration
public class DoctorControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorService doctorService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    public void shouldGetDoctorByEmail() throws Exception {
        final Doctor doctor = new Doctor();
        doctor.setEmail("test@test.com");

        when(doctorService.findByEmail(doctor.getEmail())).thenReturn(doctor);

        mockMvc.perform(get("/" + doctor.getEmail()))
                .andExpect(jsonPath("$.email").value(doctor.getEmail()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCurrentDoctor() throws Exception {
        final Doctor doctor = new Doctor();
        doctor.setEmail("test@test.com");

        when(doctorService.findByEmail(doctor.getEmail())).thenReturn(doctor);

        when(doctorService.findByEmail(doctor.getEmail())).thenReturn(doctor);

        mockMvc.perform(get("/current").principal(new UserPrincipal(doctor.getEmail())))
                .andExpect(jsonPath("$.email").value(doctor.getEmail()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateDoctor() throws Exception {
        Doctor doctor = new Doctor();

        doctor.setFirstName("Test 1");
        doctor.setLastName("Test 2");
        doctor.setEmail("test@test.com");
        doctor.setPassword("testPassword01");
        doctor.setAddress("Test Address");
        doctor.setPhoneNumber("0700000000");

        String json = mapper.writeValueAsString(doctor);

        mockMvc.perform(post("/create").principal(new UserPrincipal(doctor.getEmail())).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldFailOnCreateNewDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setEmail("test");

        String json = mapper.writeValueAsString(doctor);

        mockMvc.perform(post("/").principal(new UserPrincipal(doctor.getEmail())).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

}
