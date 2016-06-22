package com.clinical.management.action;

import com.clinical.management.dao.DoctorDAO;
import com.clinical.management.dao.DoctorDAOImpl;
import com.clinical.management.model.Doctor;
import com.clinical.management.model.Patient;
import com.clinical.management.service.DoctorService;
import com.clinical.management.service.Security;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware, ModelDriven<Doctor>, ServletContextAware {

    @Override
    public String execute() throws Exception {

        if (session.containsKey("doctor")) {
            doctor = (Doctor) session.get("doctor");
        } else if (username != null && password != null) {
            Security security = new Security();
            SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
            DoctorDAO dao = new DoctorDAOImpl(sf);

            doctor = dao.getDoctorByCredentials(username, security.encrypt(password));
        }

        if (doctor != null) {
            if (!session.containsKey("doctor"))
                session.put("doctor", doctor);

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

    // For SessionAware
    Map<String, Object> session;

    private ServletContext ctx;

    @Override
    public void setServletContext(ServletContext sc) {
        this.ctx = sc;
    }

    @Override
    public Doctor getModel() {
        return doctor;
    }

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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
