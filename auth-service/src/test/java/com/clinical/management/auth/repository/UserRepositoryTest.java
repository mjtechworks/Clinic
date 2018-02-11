package com.clinical.management.auth.repository;

import com.clinical.management.auth.AuthApplication;
import com.clinical.management.auth.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void shouldSaveAndFindUserByName() {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("password123");
        repository.save(user);

        User found = repository.findOne(user.getUsername());
        assertEquals(user.getUsername(), found.getUsername());
        assertEquals(user.getPassword(), found.getPassword());
    }
}
