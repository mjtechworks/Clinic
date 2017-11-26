package com.clinical.management.appointment.controller;

import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.repository.AppointmentRepository;
import com.clinical.management.appointment.util.AppointmentUtil;
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
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
@WebAppConfiguration
public class AppointmentControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentRepository appointmentRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    public void getAppointmentById() throws Exception {
        Appointment appointment = AppointmentUtil.getAppointment();

        when(appointmentRepository.findOne(appointment.getId())).thenReturn(appointment);

        mockMvc.perform(get("/" + appointment.getId()))
                .andExpect(jsonPath("$.doctorId").value(appointment.getDoctorId()))
                .andExpect(jsonPath("$.patientId").value(appointment.getPatientId()))
                .andExpect(jsonPath("$.startDate").value(appointment.getStartDate()))
                .andExpect(jsonPath("$.endDate").value(appointment.getEndDate()))
                .andExpect(jsonPath("$.remark").value(appointment.getRemark()))
                .andExpect(jsonPath("$.recommendation").value(appointment.getRecommendation()))
                .andExpect(jsonPath("$.reason").value(appointment.getReason()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllAppointment() throws Exception {
        when(appointmentRepository.findAll()).thenReturn(AppointmentUtil.getAppointments());

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveAppointment() throws Exception {
        Appointment appointment = AppointmentUtil.getAppointment();

        String json = mapper.writeValueAsString(appointment);

        mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

}
