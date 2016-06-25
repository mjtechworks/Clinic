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


public class UpdatePatientAction extends ActionSupport implements SessionAware, ModelDriven<Doctor>, ServletContextAware {

    @Override
    public String execute() throws Exception {

        if (!session.containsKey("doctor") || !session.containsKey("patientID"))
            return "ERROR";

        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        PatientDAO dao = new PatientDAOImpl(sf);

        patientID = (int) session.get("patientID");
        doctor = (Doctor) session.get("doctor");

        session.remove("patientID");

        patient.setDoctorID(doctor.getId());
        patient.setId(patientID);

        dao.updatePatient(patient);

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
        Date date = null;

        try {
            date = df.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date.after(new Date()))
            addFieldError("dob", "The Date of Birth must be in past !");
        else
            patient.setDob(date);

    }

    private Doctor doctor;

    private Map<String, Object> session;

    private List<Patient> patients;

    private ServletContext ctx;

    private int patientID;

    private Patient patient;

    private String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
