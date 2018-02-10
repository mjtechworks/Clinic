package com.clinical.management.appointment.service;

import com.clinical.management.appointment.client.DoctorClientService;
import com.clinical.management.appointment.client.PatientClientService;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.domain.Doctor;
import com.clinical.management.appointment.domain.Patient;
import com.clinical.management.appointment.domain.Status;
import com.clinical.management.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository repository;
    private EmailService emailService;
    private PatientClientService patientClientService;
    private DoctorClientService doctorClientService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository, EmailService emailService,
                                  PatientClientService patientClientService, DoctorClientService doctorClientService) {
        this.repository = repository;
        this.emailService = emailService;
        this.patientClientService = patientClientService;
        this.doctorClientService = doctorClientService;
    }

    @Override
    public List<Appointment> findAllAppointments(String doctorEmail, String patientId) {
        if (patientId == null || patientId.isEmpty()) {
            return repository.findAllByDoctorEmail(doctorEmail);
        } else {
            return repository.findAllByDoctorEmailAndPatientId(doctorEmail, patientId);
        }
    }

    @Override
    public Appointment save(Appointment appointment) throws MessagingException {
        Doctor doctor = doctorClientService.getDoctor(appointment.getDoctorEmail());
        Patient patient = patientClientService.getPatient(appointment.getPatientId());
        String message = getNewAppointmentMessage(appointment, patient, doctor);

        emailService.sendEmail(patient.getEmail(), "New Appointment", message);

        return repository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) throws MessagingException {
        Doctor doctor = doctorClientService.getDoctor(appointment.getDoctorEmail());
        Patient patient = patientClientService.getPatient(appointment.getPatientId());

        if (appointment.getStatus() == Status.CLOSE) {
            sendCloseAppointmentMessage(appointment, patient, doctor);
        } else if (appointment.getStatus() == Status.DONE) {
            sendDoneAppointmentMessage(appointment, patient, doctor);
        }

        return repository.save(appointment);
    }

    private void sendCloseAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) throws MessagingException {
        String message = getCloseAppointmentMessage(appointment, patient, doctor);
        emailService.sendEmail(patient.getEmail(), "Close Appointment", message);
    }

    private void sendDoneAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) throws MessagingException {
        String message = getDoneAppointmentMessage(appointment, patient, doctor);
        emailService.sendEmail(patient.getEmail(), "Appointment done", message);
    }

    private String getDoneAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) {
        return helloMessage(patient) + doneAppointmentMessage(appointment, doctor) + contactMessage(doctor) + regardMessage();
    }

    private String doneAppointmentMessage(Appointment appointment, Doctor doctor) {
        return String.format("The appointment between %tc - %tc with doctor %s %s was done. \n" +
                        "You have a recommendation ' %s ' \n",
                appointment.getStartDate(), appointment.getEndDate(), doctor.getFirstName(),
                doctor.getLastName(), appointment.getRecommendation());
    }

    private String getNewAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) {
        return helloMessage(patient) + newAppointmentMessage(appointment, doctor) + contactMessage(doctor) + regardMessage();
    }

    private String getCloseAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) {
        return helloMessage(patient) + closeAppointmentMessage(appointment, doctor) + contactMessage(doctor) + regardMessage();
    }

    private String helloMessage(Patient patient) {
        return String.format("Hello %s %s, \n \n", patient.getFirstName(), patient.getLastName());
    }

    private String contactMessage(Doctor doctor) {
        return String.format("If you have any problem please contact the doctor at the phone number %s or at email address %s. \n \n \n",
                doctor.getPhoneNumber(), doctor.getEmail());
    }

    private String regardMessage() {
        return "Best regards !";
    }

    private String closeAppointmentMessage(Appointment appointment, Doctor doctor) {
        return String.format("The appointment between %tc - %tc with doctor %s %s was closed for the next reason ' %s ' \n",
                appointment.getStartDate(), appointment.getEndDate(), doctor.getFirstName(), doctor.getLastName(), appointment.getReason());
    }

    private String newAppointmentMessage(Appointment appointment, Doctor doctor) {
        return String.format("You have a new appointment between %tc - %tc with doctor %s %s. \n",
                appointment.getStartDate(), appointment.getEndDate(), doctor.getFirstName(), doctor.getLastName());
    }

}
