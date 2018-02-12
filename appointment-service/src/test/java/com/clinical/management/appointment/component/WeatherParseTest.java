package com.clinical.management.appointment.component;

import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.client.WeatherServiceClient;
import com.clinical.management.appointment.domain.Weather;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class WeatherParseTest {

    @InjectMocks
    private WeatherParse weatherParser;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Value("${weather.id}")
    private Integer id;

    @Value("${weather.APPID}")
    private String appid;

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    @Test
    public void shouldGetWeathers() throws Exception {
        JSONObject jsonObject = weatherServiceClient.getWeather(id, appid, 45.0, 25.0);
        List<Weather> weathers = weatherParser.parse(jsonObject);

        assertNotNull(weathers);
    }

}
