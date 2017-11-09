package com.clinical.management.doctor.controller;


import com.clinical.management.doctor.DoctorApplication;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.repository.DoctorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private DoctorRepository doctorRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    public void getDoctorById() throws Exception {
        Doctor doctor = new Doctor();

        doctor.setId("55");
        doctor.setFullName("Test");
        doctor.setEmail("test@test.com");

        when(doctorRepository.findOne(doctor.getId())).thenReturn(doctor);

        mockMvc.perform(get("/" + doctor.getId()))
                .andExpect(jsonPath("$.fullName").value(doctor.getFullName()))
                .andExpect(jsonPath("$.email").value(doctor.getEmail()))
                .andExpect(status().isOk());
    }

    @Test
    public void saveDoctor() throws Exception {
        Doctor doctor = new Doctor();

        doctor.setId("55");
        doctor.setFullName("Test");
        doctor.setEmail("test@test.com");

        String json = mapper.writeValueAsString(doctor);

        mockMvc.perform(post("").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

}
