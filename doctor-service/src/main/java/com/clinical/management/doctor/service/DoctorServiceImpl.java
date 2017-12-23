package com.clinical.management.doctor.service;

import com.clinical.management.doctor.client.AuthServiceClient;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.User;
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
    private AuthServiceClient authClient;

    @Autowired
    public DoctorServiceImpl(DoctorRepository repository, AuthServiceClient authClient) {
        this.repository = repository;
        this.authClient = authClient;
    }

    @Override
    public Doctor findByEmail(String doctorEmail) {
        Assert.hasLength(doctorEmail, "Doctor email is empty ! ");
        return repository.findDoctorByEmail(doctorEmail);
    }

    @Override
    public Doctor create(Doctor doctor) {
        Doctor existing = repository.findDoctorByEmail(doctor.getEmail());
        Assert.isNull(existing, "Doctor already exists: " + doctor.getEmail());

        User user = new User();
        user.setUsername(doctor.getEmail());
        user.setPassword(doctor.getPassword());

        authClient.createUser(user);

        doctor.setPassword(null);

        repository.save(doctor);

        return doctor;
    }
}
