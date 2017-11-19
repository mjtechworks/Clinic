package com.clinical.management.patient.cotroller;

import com.clinical.management.patient.PatientApplication;
import com.clinical.management.patient.controller.PatientController;
import com.clinical.management.patient.domain.Patient;
import com.clinical.management.patient.repository.PatientRepository;
import com.clinical.management.patient.util.PatientUtil;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PatientApplication.class)
@WebAppConfiguration
public class PatientControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientRepository patientRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    public void getPatientById() throws Exception {
        Patient patient = PatientUtil.getPatient();

        when(patientRepository.findOne(patient.getId())).thenReturn(patient);

        mockMvc.perform(get("/" + patient.getId()))
                .andExpect(jsonPath("$.firstName").value(patient.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(patient.getLastName()))
                .andExpect(jsonPath("$.email").value(patient.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(patient.getPhoneNumber()))
                .andExpect(jsonPath("$.height").value(patient.getHeight()))
                .andExpect(jsonPath("$.weight").value(patient.getWeight()))
                .andExpect(jsonPath("$.dateOfBirth").value(patient.getDateOfBirth()))
                .andExpect(jsonPath("$.gender").value(patient.getGender()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllPatients() throws Exception {
        when(patientRepository.findAll()).thenReturn(PatientUtil.getPatients());

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk());
    }


    @Test
    public void savePatient() throws Exception {
        String json = mapper.writeValueAsString(PatientUtil.getPatient());

        mockMvc.perform(post("").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

}
