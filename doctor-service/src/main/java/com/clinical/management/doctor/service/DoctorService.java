package com.clinical.management.doctor.service;

import com.clinical.management.doctor.domain.Doctor;

public interface DoctorService {

    /**
     * Finds account by given name
     *
     * @param doctorEmail doctor email
     * @return found account
     */
    Doctor findByEmail(String doctorEmail);


    /**
     * Checks if account with the same name already exists
     * Invokes Auth Service user creation
     * Creates new doctor with default parameters
     *
     * @param doctor doctor credential
     * @return created account
     */
    Doctor create(Doctor doctor);


    /**
     * Update doctor information (first name, last name, address, phone number)
     *
     * @param doctor new doctor information
     * @return doctor updated
     */
    Doctor update(Doctor doctor);

}
