package com.clinical.management.appointment.service;

import com.clinical.management.appointment.client.DoctorClientService;
import com.clinical.management.appointment.client.PatientClientService;
import com.clinical.management.appointment.domain.Appointment;
import com.clinical.management.appointment.domain.Doctor;
import com.clinical.management.appointment.domain.Patient;
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

    private String getNewAppointmentMessage(Appointment appointment, Patient patient, Doctor doctor) {
        return String.format("Hello %s %s, \n \n" +
                        "You have a new appointment between %tc - %tc with doctor %s %s. \n" +
                        "If you have any problem please contact the doctor at the phone number %s or at email address %s. \n \n \n" +
                        "Best regards !", patient.getFirstName(), patient.getLastName(), appointment.getStartDate(), appointment.getEndDate(),
                doctor.getFirstName(), doctor.getLastName(), doctor.getPhoneNumber(), doctor.getEmail());
    }

}
