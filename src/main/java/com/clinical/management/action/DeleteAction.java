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

public class DeleteAction extends ActionSupport implements SessionAware, ModelDriven<Doctor>, ServletContextAware {

    @Override
    public String execute() throws Exception {

        if (!session.containsKey("doctor") || patientID == 0)
            return "ERROR";

        doctor = (Doctor) session.get("doctor");

        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        PatientDAO dao = new PatientDAOImpl(sf);

        dao.deletePatientById(patientID);

        patients = dao.getPatientsByDoctorID(doctor.getId());

        return "SUCCESS";
    }

    private Doctor doctor;

    Map<String, Object> session;

    private ServletContext ctx;

    private int patientID;

    private List<Patient> patients;

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
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
