package com.clinical.management.dao;

import com.clinical.management.model.Doctor;

public interface DoctorDAO {

    Doctor getDoctorByCredentials(String userName, String password);

}
