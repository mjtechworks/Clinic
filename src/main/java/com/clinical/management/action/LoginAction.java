package com.clinical.management.action;

import com.clinical.management.model.Doctor;
import com.clinical.management.service.DoctorService;
import com.opensymphony.xwork2.Action;

public class LoginAction implements Action {

    @Override
    public String execute() throws Exception {

        Doctor doctor = DoctorService.findDoctor(username, password);

        if (doctor != null) {
            setFirstName(doctor.getFirstName());
            setLastName(doctor.getLastName());

            return "SUCCESS";
        } else
            return "ERROR";

    }

    //Java Bean to hold the form parameters
    private String username;

    private String password;

    private String firstName;

    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
