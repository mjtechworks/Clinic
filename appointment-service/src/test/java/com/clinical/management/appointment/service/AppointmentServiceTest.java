package com.clinical.management.appointment.service;


import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.client.DoctorClientService;
import com.clinical.management.appointment.client.PatientClientService;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.domain.Doctor;
import com.clinical.management.appointment.domain.Patient;
import com.clinical.management.appointment.repository.AppointmentRepository;
import com.clinical.management.appointment.util.AppointmentUtil;
import com.clinical.management.appointment.util.DoctorUtil;
import com.clinical.management.appointment.util.PatientUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Test
    public void shouldSaveAppointment() throws ParseException, MessagingException {
        Appointment appointment = AppointmentUtil.getAppointment();

        when(repository.save(appointment)).thenReturn(appointment);

        Appointment saved = appointmentService.save(appointment);

        compareAppointments(appointment, saved);
    }

    @Test
    public void shouldSendEmail() throws ParseException, MessagingException {
        Appointment appointment = AppointmentUtil.getAppointment();
        Patient patient = PatientUtil.getPatient();
        Doctor doctor = DoctorUtil.getDoctor();
        String message = getNewAppointmentMessage(appointment, patient, doctor);

        when(doctorClientService.getDoctor(appointment.getDoctorEmail())).thenReturn(doctor);
        when(patientClientService.getPatient(appointment.getPatientId())).thenReturn(patient);
        when(repository.save(appointment)).thenReturn(appointment);

        appointmentService.save(appointment);

        verify(emailService, timeout(100)).sendEmail(patient.getEmail(), "New Appointment", message);
    }

    private String getNewAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) {
        return String.format("Hello %s %s, \n \n" +
                        "You have a new appointment between %tc - %tc with doctor %s %s. \n" +
                        "If you have any problem please contact the doctor at the phone number %s or at email address %s. \n \n \n" +
                        "Best regards !", patient.getFirstName(), patient.getLastName(), appointment.getStartDate(), appointment.getEndDate(),
                doctor.getFirstName(), doctor.getLastName(), doctor.getPhoneNumber(), doctor.getEmail());
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
