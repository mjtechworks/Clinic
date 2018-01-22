package com.clinical.management.appointment.service;


import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.repository.AppointmentRepository;
import com.clinical.management.appointment.util.AppointmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class AppointmentServiceTst {

    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void findAllAppointmentsTest() throws ParseException {
        Appointment appointment = AppointmentUtil.getAppointment();
        List<Appointment> appointments = AppointmentUtil.getAppointments();

        when(repository.findAllByDoctorEmailAndPatientId("test@test.com", "550"))
                .thenReturn(Collections.singletonList(appointment));
        when(repository.findAllByDoctorEmail("test@test.com")).thenReturn(appointments);

        List<Appointment> foundAppointment = appointmentService.findAllAppointments("test@test.com", "550");
        List<Appointment> foundAppointments = appointmentService.findAllAppointments("test@test.com", null);

        assertEquals(foundAppointment.size(), 1);
        assertEquals(foundAppointments.size(), appointments.size());
    }

}
