package com.clinical.management.auth.service;

import com.clinical.management.auth.AuthApplication;
import com.clinical.management.auth.domain.User;
import com.clinical.management.auth.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldCreateUser() {

        User user = new User();
        user.setUsername("name");
        user.setPassword("password");

        userService.create(user);
        verify(repository, times(1)).save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUserAlreadyExists() {

        User user = new User();
        user.setUsername("name");
        user.setPassword("password");

        when(repository.findOne(user.getUsername())).thenReturn(new User());
        userService.create(user);
    }

}
