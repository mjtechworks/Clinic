package com.clinical.management.appointment.client;

import com.clinical.management.appointment.AppointmentApplication;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class WeatherServiceClientTest {

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Value("${weather.id}")
    private Integer id;

    @Value("${weather.APPID}")
    private String appid;

    @Test
    public void shouldGetJsonWeather() {
        JSONObject jsonObject = weatherServiceClient.getWeather(id, appid, 45.0, 25.0);

        assertNotNull(jsonObject);
    }

}
