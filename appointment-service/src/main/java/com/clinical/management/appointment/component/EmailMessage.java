package com.clinical.management.appointment.component;

import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.domain.Doctor;
import com.clinical.management.appointment.domain.Patient;
import org.springframework.stereotype.Component;

@Component
public class EmailMessage {
    public String getMessage(Appointment appointment, Patient patient, Doctor doctor) {
        return helloMessage(patient) + appointmentStatusMessage(appointment, doctor) + contactMessage(doctor) + regardMessage();
    }

    private String appointmentStatusMessage(Appointment appointment, Doctor doctor) {
        switch (appointment.getStatus()) {
            case OPEN:
                return newAppointmentMessage(appointment, doctor);
            case CLOSE:
                return closeAppointmentMessage(appointment, doctor);
            case DONE:
                return doneAppointmentMessage(appointment, doctor);
            default:
                return "";
        }
    }

    private String newAppointmentMessage(Appointment appointment, Doctor doctor) {
        return String.format("You have a new appointment between %tc - %tc with doctor %s %s. %n",
                appointment.getStartDate(), appointment.getEndDate(), doctor.getFirstName(), doctor.getLastName());
    }

    private String closeAppointmentMessage(Appointment appointment, Doctor doctor) {
        return String.format("The appointment between %tc - %tc with doctor %s %s was closed for the next reason ' %s ' %n",
                appointment.getStartDate(), appointment.getEndDate(), doctor.getFirstName(), doctor.getLastName(), appointment.getReason());
    }

    private String doneAppointmentMessage(Appointment appointment, Doctor doctor) {
        return String.format("The appointment between %tc - %tc with doctor %s %s was done. %n" +
                        "You have a recommendation ' %s ' %n",
                appointment.getStartDate(), appointment.getEndDate(), doctor.getFirstName(),
                doctor.getLastName(), appointment.getRecommendation());
    }

    private String helloMessage(Patient patient) {
        return String.format("Hello %s %s, %n %n", patient.getFirstName(), patient.getLastName());
    }

    private String contactMessage(Doctor doctor) {
        return String.format("If you have any problem please contact the doctor at the phone number %s or at email address %s. %n %n %n",
                doctor.getPhoneNumber(), doctor.getEmail());
    }

    private String regardMessage() {
        return "Best regards !";
    }

}
