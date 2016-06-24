package com.clinical.management.action;


import com.clinical.management.dao.PatientDAO;
import com.clinical.management.dao.PatientDAOImpl;
import com.clinical.management.model.Doctor;
import com.clinical.management.model.Patient;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreatePatientAction extends ActionSupport implements SessionAware, ModelDriven<Doctor>, ServletContextAware {

    @Override
    public String execute() throws Exception {

        if (!session.containsKey("doctor"))
            return "ERROR";

        doctor = (Doctor) session.get("doctor");

        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        PatientDAO dao = new PatientDAOImpl(sf);

        patient.setDoctorID(doctor.getId());

        dao.addPatient(patient);

        patients = dao.getPatientsByDoctorID(doctor.getId());

        return "SUCCESS";
    }

    public void validate() {
        if (patient.getFirstName() == null || patient.getFirstName().equals("")) {
            addFieldError("patient.firstName", "This field is required");
        }

        if (patient.getLastName() == null || patient.getLastName().equals("")) {
            addFieldError("patient.lastName", "This field is required");
        }

        if (patient.getAddress() == null || patient.getAddress().equals("")) {
            addFieldError("patient.address", "This field is required");
        }

        if (patient.getPhoneNumber() == null || patient.getPhoneNumber().equals("")) {
            addFieldError("patient.firstName", "This field is required");
        }

        if (dob == null || dob.equals("")) {
            addFieldError("dob", "This field is required");
        }

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        try {
            patient.setDob(df.parse(dob));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Doctor doctor;

    private Patient patient;

    private List<Patient> patients;

    private String dob;

    Map<String, Object> session;

    private ServletContext ctx;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public void setServletContext(ServletContext sc) {
        this.ctx = sc;
    }

    @Override
    public Doctor getModel() {
        return doctor;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
