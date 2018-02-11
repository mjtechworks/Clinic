package com.clinical.management.appointment.repository;


import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.util.AppointmentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Test
    public void findAllAppointments() throws ParseException {
        List<Appointment> appointments = AppointmentUtil.getAppointments();
        saveAppointments(appointments);

        List<Appointment> founds = appointmentRepository.findAllByDoctorEmail("test@test.com");

        assertEquals(founds.size(), appointments.size());
        compareAppointments(founds, appointments);
    }

    @Test
    public void findAllAppointmentsByPatient() throws ParseException {
        List<Appointment> appointments = AppointmentUtil.getAppointments();
        saveAppointments(appointments);

        List<Appointment> founds = appointmentRepository.findAllByDoctorEmailAndPatientId("test@test.com", "660");

        assertEquals(founds.size(), 1);
        compareAppointments(founds, appointments);
    }

    @Test
    public void findAppointmentById() throws ParseException {
        Appointment appointment = AppointmentUtil.getAppointment();
        saveAppointments(Collections.singletonList(appointment));

        Appointment found = appointmentRepository.findOne(appointment.getId());
        compareAppointments(found, appointment);
    }

    private void saveAppointments(List<Appointment> appointments) {
        appointmentRepository.deleteAll();
        appointmentRepository.save(appointments);
    }

    private void compareAppointments(List<Appointment> app1, List<Appointment> app2) {
        for (int i = 0; i < app1.size(); i++) {
            compareAppointments(app1.get(i), app2.get(i));
        }
    }

    private void compareAppointments(@NotNull Appointment app1, @NotNull Appointment app2) {
        assertEquals(app1.getPatientId(), app2.getPatientId());
        assertEquals(app1.getDoctorEmail(), app2.getDoctorEmail());
        assertEquals(app1.getStartDate(), app2.getStartDate());
        assertEquals(app1.getStartDate(), app2.getStartDate());
        assertEquals(app1.getEndDate(), app2.getEndDate());
        assertEquals(app1.getRemark(), app2.getRemark());
        assertEquals(app1.getReason(), app2.getReason());
        assertEquals(app1.getRecommendation(), app2.getRecommendation());
        assertEquals(app1.getDescription(), app2.getDescription());
        assertEquals(app1.getStatus(), app2.getStatus());
    }

}
