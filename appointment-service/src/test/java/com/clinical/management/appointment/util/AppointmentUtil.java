package com.clinical.management.appointment.util;

import com.clinical.management.appointment.domain.Appointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppointmentUtil {

    public static Appointment getAppointment() throws ParseException {
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

        return appointment;
    }

    public static List<Appointment> getAppointments() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        List<Appointment> appointments = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Appointment appointment = new Appointment();

            appointment.setId("55" + i);
            appointment.setPatientId("66" + i);
            appointment.setDoctorId("77" + i);
            appointment.setStartDate(sdf.parse("11/11/2017 10:45"));
            appointment.setEndDate(sdf.parse("11/11/2017 11:15"));
            appointment.setRemark("Test - Remark - " + i);
            appointment.setRecommendation("Test - Recommendation - " + i);
            appointment.setReason("Test - Reason - " + i);

            appointments.add(appointment);
        }

        return appointments;
    }

}
