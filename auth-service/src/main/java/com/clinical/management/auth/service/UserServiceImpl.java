package com.clinical.management.auth.service;


import com.clinical.management.auth.domain.User;
import com.clinical.management.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {

        User existing = repository.findOne(user.getUsername());
        Assert.isNull(existing, "User already exists: " + user.getUsername());

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("New user has been created: {}", user.getUsername());
    }
}
