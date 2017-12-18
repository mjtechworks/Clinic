package com.clinical.management.doctor.service.security;

import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.User;
import com.clinical.management.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private DoctorRepository repository;

    @Autowired
    public UserDetailsServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = repository.findDoctorByEmail(username);

        if (doctor == null) {
            throw new UsernameNotFoundException(username);
        }

        User user = new User();
        user.setId(doctor.getId());
        user.setPassword(doctor.getPassword());
        user.setUsername(doctor.getEmail());

        return user;

    }
}
