package com.clinical.management.appointment.repository;


import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Appointment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Test
    public void findDoctorById() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm");

        Appointment appointment = new Appointment();

        appointment.setId("55");
        appointment.setPatientId("66");
        appointment.setDoctorId("77");
        appointment.setStartDate(sdf.parse("11/11/2017 10:45"));
        appointment.setEndDate(sdf.parse("11/11/2017 11:15"));
        appointment.setRemark("Test - Remark");
        appointment.setRecommendation("Test - Recommendation");
        appointment.setReason("Test - Reason");

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
