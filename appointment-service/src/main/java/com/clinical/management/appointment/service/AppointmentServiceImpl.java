package com.clinical.management.appointment.service;

import com.clinical.management.appointment.client.DoctorClientService;
import com.clinical.management.appointment.client.PatientClientService;
import com.clinical.management.appointment.component.EmailMessage;
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
    private EmailMessage emailMessage;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository, EmailService emailService, PatientClientService patientClientService,
                                  DoctorClientService doctorClientService, EmailMessage emailMessage) {
        this.repository = repository;
        this.emailService = emailService;
        this.patientClientService = patientClientService;
        this.doctorClientService = doctorClientService;
        this.emailMessage = emailMessage;
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
        String message = emailMessage.getMessage(appointment, patient, doctor);

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
        String message = emailMessage.getMessage(appointment, patient, doctor);
        emailService.sendEmail(patient.getEmail(), "Close Appointment", message);
    }

    private void sendDoneAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) throws MessagingException {
        String message = emailMessage.getMessage(appointment, patient, doctor);
        emailService.sendEmail(patient.getEmail(), "Appointment done", message);
    }

}
