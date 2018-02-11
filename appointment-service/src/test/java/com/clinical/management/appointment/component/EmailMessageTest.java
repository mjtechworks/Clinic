package com.clinical.management.appointment.component;

import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.domain.Doctor;
import com.clinical.management.appointment.domain.Patient;
import com.clinical.management.appointment.domain.Status;
import com.clinical.management.appointment.util.AppointmentUtil;
import com.clinical.management.appointment.util.DoctorUtil;
import com.clinical.management.appointment.util.PatientUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class EmailMessageTest {
    private Appointment appointment;
    private Patient patient;
    private Doctor doctor;

    @InjectMocks
    private EmailMessage emailMessage;

    @Before
    public void setup() throws ParseException {
        initMocks(this);
        this.appointment = AppointmentUtil.getAppointment();
        this.doctor = DoctorUtil.getDoctor();
        this.patient = PatientUtil.getPatient();
    }

    @Test
    public void shouldGetNewAppointmentMessage() {
        String message = emailMessage.getMessage(appointment, patient, doctor);

        assertNotNull(message);
        assertTrue(message.contains("You have a new appointment"));
    }

    @Test
    public void shouldGetCloseAppointmentMessage() {
        appointment.setStatus(Status.CLOSE);
        String message = emailMessage.getMessage(appointment, patient, doctor);

        assertNotNull(message);
        assertTrue(message.contains("was closed for the next reason"));
    }

    @Test
    public void shouldGetDoneAppointment(){
        appointment.setStatus(Status.DONE);
        String message = emailMessage.getMessage(appointment, patient, doctor);

        assertNotNull(message);
        assertTrue(message.contains("was done"));
    }

}
