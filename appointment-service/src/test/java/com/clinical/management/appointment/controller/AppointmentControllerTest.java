package com.clinical.management.appointment.controller;

import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.repository.AppointmentRepository;
import com.clinical.management.appointment.service.AppointmentService;
import com.clinical.management.appointment.service.WeatherService;
import com.clinical.management.appointment.util.AppointmentUtil;
import com.clinical.management.appointment.util.WeatherUtil;
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
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
@WebAppConfiguration
public class AppointmentControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static List<Appointment> appointments;
    private static Appointment appointment;

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private WeatherService weatherService;

    private MockMvc mockMvc;

    @Before
    public void setup() throws ParseException {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        appointments = AppointmentUtil.getAppointments();
        appointment = AppointmentUtil.getAppointment();
    }

    @Test
    public void getAppointmentById() throws Exception {
        when(appointmentRepository.findOne(appointment.getId())).thenReturn(appointment);

        mockMvc.perform(get("/" + appointment.getId()))
                .andExpect(jsonPath("$.doctorEmail").value(appointment.getDoctorEmail()))
                .andExpect(jsonPath("$.patientId").value(appointment.getPatientId()))
                .andExpect(jsonPath("$.startDate").value(appointment.getStartDate()))
                .andExpect(jsonPath("$.endDate").value(appointment.getEndDate()))
                .andExpect(jsonPath("$.remark").value(appointment.getRemark()))
                .andExpect(jsonPath("$.recommendation").value(appointment.getRecommendation()))
                .andExpect(jsonPath("$.reason").value(appointment.getReason()))
                .andExpect(jsonPath("$.description").value(appointment.getDescription()))
                .andExpect(jsonPath("$.status").value(appointment.getStatus().name()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllAppointment() throws Exception {
        when(appointmentService.findAllAppointments("test@test.com", null)).thenReturn(appointments);

        mockMvc.perform(get("/all?doctorEmail=test@test.com"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllAppointmentByPatient() throws Exception {
        when(appointmentService.findAllAppointments("test@test.com", "550")).thenReturn(appointments);

        mockMvc.perform(get("/all?doctorEmail=test@test.com&patientId=550"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveAppointment() throws Exception {
        String json = mapper.writeValueAsString(appointment);

        mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void updateAppointment() throws Exception {
        String json = mapper.writeValueAsString(appointment);

        mockMvc.perform(put("/update").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void getWeather() throws Exception {
        when(weatherService.getWeather(45.0, 25.0)).thenReturn(WeatherUtil.getWeathers());

        mockMvc.perform(get("/weather?lat=45.0&lon=25.0")).andExpect(status().isOk());
    }

    @Test
    public void getWeatherBadRequest() throws Exception {
        when(weatherService.getWeather(45.0, 25.0)).thenReturn(WeatherUtil.getWeathers());

        mockMvc.perform(get("/weather?lon=25.0")).andExpect(status().isBadRequest());
    }

}
