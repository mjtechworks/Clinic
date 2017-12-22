package com.clinical.management.auth.service.security;

import com.clinical.management.auth.AuthApplication;
import com.clinical.management.auth.domain.User;
import com.clinical.management.auth.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class UserDetailsServiceTest {

    @InjectMocks
    private UserDetailsServiceImpl service;

    @Mock
    private UserRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldLoadByUsernameWhenUserExists() {

        final User user = new User();

        when(repository.findOne(any())).thenReturn(user);
        UserDetails loaded = service.loadUserByUsername("name");

        assertEquals(user, loaded);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldFailToLoadByUsernameWhenUserNotExists() {
        service.loadUserByUsername("name");
    }

}
