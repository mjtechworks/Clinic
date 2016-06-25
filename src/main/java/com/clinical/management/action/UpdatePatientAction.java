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


    private Doctor doctor;

    private Map<String, Object> session;

    private List<Patient> patients;

    private ServletContext ctx;

    private int patientID;

    private Patient patient;

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
