package com.clinical.management.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;


public class LogoutAction extends ActionSupport implements SessionAware {
    Map<String, Object> session;

    @Override
    public String execute() throws Exception {

        session.clear();

        return "SUCCESS";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
