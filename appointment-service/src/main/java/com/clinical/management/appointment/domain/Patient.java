package com.clinical.management.appointment.domain;


public class Patient extends Person {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
