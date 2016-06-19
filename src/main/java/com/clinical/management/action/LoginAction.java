package com.clinical.management.action;

import com.opensymphony.xwork2.Action;

public class LoginAction implements Action {

    @Override
    public String execute() throws Exception {
        if("daniellungu".equals(getUsername()) && "1234567890".equals(getPassword()))
            return "SUCCESS";
        else return "ERROR";
    }

    //Java Bean to hold the form parameters
    private String username;

    private String password;

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

}
