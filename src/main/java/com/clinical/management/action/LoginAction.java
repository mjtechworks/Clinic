package com.clinical.management.action;

import com.clinical.management.model.Doctor;
import com.clinical.management.model.Patient;
import com.clinical.management.service.DoctorService;
import com.opensymphony.xwork2.Action;

import java.util.List;

public class LoginAction implements Action {

    @Override
    public String execute() throws Exception {

        doctor = DoctorService.findDoctor(username, password);

        if (doctor != null) {
            patients = DoctorService.getAllDoctorPatients(doctor.getId());

            return "SUCCESS";
        } else
            return "ERROR";

    }

    //Java Bean to hold the form parameters
    private String username;

    private String password;

    private Doctor doctor;

    private List<Patient> patients;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
