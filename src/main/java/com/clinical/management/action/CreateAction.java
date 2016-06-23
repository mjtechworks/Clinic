package com.clinical.management.action;

import com.clinical.management.model.Doctor;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;


public class CreateAction extends ActionSupport implements SessionAware, ModelDriven<Doctor>, ServletContextAware {

    @Override
    public String execute() throws Exception {

        if (!session.containsKey("doctor"))
            return "ERROR";

        doctor = (Doctor) session.get("doctor");

        return "SUCCESS";
    }


    private Doctor doctor;

    Map<String, Object> session;

    private ServletContext ctx;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
