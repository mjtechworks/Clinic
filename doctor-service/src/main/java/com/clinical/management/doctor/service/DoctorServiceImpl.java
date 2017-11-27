package com.clinical.management.doctor.service;

import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
public class DoctorServiceImpl implements DoctorService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private DoctorRepository repository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(Doctor doctor) {
        Doctor existing = repository.findDoctorByEmail(doctor.getEmail());
        Assert.isNull(existing, "Doctor with email  " + doctor.getEmail() + " already exit !");

        String hash = encoder.encode(doctor.getPassword());
        doctor.setPassword(hash);

        repository.save(doctor);

        log.info("New doctor has been created: {}", doctor.getFirstName() + " " + doctor.getLastName());

        return true;
    }
}
