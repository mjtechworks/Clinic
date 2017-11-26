package com.clinical.management.appointment.repository;


import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.util.AppointmentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Test
    public void findAllPatients() throws ParseException {
        List<Appointment> appointments = AppointmentUtil.getAppointments();

        appointmentRepository.save(appointments);

        List<Appointment> founds = appointmentRepository.findAll();

        assertEquals(founds.size(), 5);

        for (int i = 0; i < 5; i++) {
            Appointment appointment = appointments.get(i);
            Appointment found = founds.get(i);

            assertEquals(appointment.getPatientId(), found.getPatientId());
            assertEquals(appointment.getDoctorId(), found.getDoctorId());
            assertEquals(appointment.getStartDate(), found.getStartDate());
            assertEquals(appointment.getStartDate(), found.getStartDate());
            assertEquals(appointment.getEndDate(), found.getEndDate());
            assertEquals(appointment.getRemark(), found.getRemark());
            assertEquals(appointment.getReason(), found.getReason());
            assertEquals(appointment.getRecommendation(), found.getRecommendation());
        }
    }

    @Test
    public void findDoctorById() throws ParseException {
        Appointment appointment = AppointmentUtil.getAppointment();

        appointmentRepository.save(appointment);

        Appointment found = appointmentRepository.findOne(appointment.getId());

        assertEquals(appointment.getPatientId(), found.getPatientId());
        assertEquals(appointment.getDoctorId(), found.getDoctorId());
        assertEquals(appointment.getStartDate(), found.getStartDate());
        assertEquals(appointment.getStartDate(), found.getStartDate());
        assertEquals(appointment.getEndDate(), found.getEndDate());
        assertEquals(appointment.getRemark(), found.getRemark());
        assertEquals(appointment.getReason(), found.getReason());
        assertEquals(appointment.getRecommendation(), found.getRecommendation());
    }

}
