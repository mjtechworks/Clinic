package com.clinical.management.auth.service.security;

import com.clinical.management.auth.domain.User;
import com.clinical.management.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findOne(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
