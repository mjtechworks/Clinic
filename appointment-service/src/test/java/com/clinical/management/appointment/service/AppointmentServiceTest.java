package com.clinical.management.appointment.service;


import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.client.DoctorClientService;
import com.clinical.management.appointment.client.PatientClientService;
import com.clinical.management.appointment.component.EmailMessage;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.domain.Doctor;
import com.clinical.management.appointment.domain.Patient;
import com.clinical.management.appointment.domain.Status;
import com.clinical.management.appointment.repository.AppointmentRepository;
import com.clinical.management.appointment.util.AppointmentUtil;
import com.clinical.management.appointment.util.DoctorUtil;
import com.clinical.management.appointment.util.PatientUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class AppointmentServiceTest {
    private Appointment appointment;
    private List<Appointment> appointments;
    private Patient patient;
    private Doctor doctor;

    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Mock
    private EmailService emailService;

    @Mock
    private PatientClientService patientClientService;

    @Mock
    private DoctorClientService doctorClientService;

    @Mock
    private EmailMessage emailMessageMock;

    @Autowired
    private EmailMessage emailMessage;

    @Before
    public void setup() throws ParseException {
        initMocks(this);
        this.appointment = AppointmentUtil.getAppointment();
        this.appointments = AppointmentUtil.getAppointments();
        this.doctor = DoctorUtil.getDoctor();
        this.patient = PatientUtil.getPatient();
    }

    @Test
    public void findAllAppointmentsTest() {
        when(repository.findAllByDoctorEmailAndPatientId("test@test.com", "550"))
                .thenReturn(Collections.singletonList(appointment));
        when(repository.findAllByDoctorEmail("test@test.com")).thenReturn(appointments);

        List<Appointment> foundAppointment = appointmentService.findAllAppointments("test@test.com", "550");
        List<Appointment> foundAppointmentsNullPatientId = appointmentService.findAllAppointments("test@test.com", null);
        List<Appointment> foundAppointmentsEmptyPatientId = appointmentService.findAllAppointments("test@test.com", "");

        assertEquals(foundAppointment.size(), 1);
        assertEquals(foundAppointmentsNullPatientId.size(), appointments.size());
        assertEquals(foundAppointmentsEmptyPatientId.size(), appointments.size());
    }

    @Test
    public void shouldSaveAppointmentAndSendEmail() throws MessagingException {
        String message = emailMessage.getMessage(appointment, patient, doctor);

        setBehavior(message);

        Appointment saved = appointmentService.save(appointment);

        verify(emailService, timeout(100)).sendEmail(patient.getEmail(), "New Appointment", message);
        compareAppointments(appointment, saved);
    }

    @Test
    public void shouldUpdateAppointmentAndSendCloseEmail() throws MessagingException {
        String message = emailMessage.getMessage(appointment, patient, doctor);
        appointment.setStatus(Status.CLOSE);

        setBehavior(message);

        Appointment update = appointmentService.update(appointment);

        verify(emailService, timeout(100)).sendEmail(patient.getEmail(), "Close Appointment", message);
        compareAppointments(appointment, update);
    }

    @Test
    public void shouldUpdateAppointmentAndSendDoneEmail() throws MessagingException {
        String message = emailMessage.getMessage(appointment, patient, doctor);
        appointment.setStatus(Status.DONE);

        setBehavior(message);

        Appointment update = appointmentService.update(appointment);

        verify(emailService, timeout(100)).sendEmail(patient.getEmail(), "Appointment done", message);
        compareAppointments(appointment, update);
    }

    private void setBehavior(String message) {
        when(doctorClientService.getDoctor(appointment.getDoctorEmail())).thenReturn(doctor);
        when(patientClientService.getPatient(appointment.getPatientId())).thenReturn(patient);
        when(repository.save(appointment)).thenReturn(appointment);
        when(emailMessageMock.getMessage(appointment, patient, doctor)).thenReturn(message);
    }

    private void compareAppointments(Appointment app1, Appointment app2) {
        assertEquals(app1.getDescription(), app2.getDescription());
        assertEquals(app1.getDoctorEmail(), app2.getDoctorEmail());
        assertEquals(app1.getPatientId(), app2.getPatientId());
        assertEquals(app1.getStatus(), app2.getStatus());
        assertEquals(app1.getStartDate(), app2.getStartDate());
        assertEquals(app1.getEndDate(), app2.getEndDate());
        assertEquals(app1.getReason(), app2.getReason());
        assertEquals(app1.getRemark(), app2.getRemark());
        assertEquals(app1.getRecommendation(), app2.getRecommendation());
    }

}
