package com.clinical.management.appointment.service;

import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.client.WeatherServiceClient;
import com.clinical.management.appointment.domain.Weather;
import com.clinical.management.appointment.parser.WeatherParse;
import com.clinical.management.appointment.util.WeatherUtil;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppointmentApplication.class)
public class WeatherServiceTest {

    @Mock
    private WeatherServiceClient weatherServiceClientMock;

    @Mock
    private WeatherParse weatherParser;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    @Value("${weather.id}")
    private Integer id;

    @Value("${weather.APPID}")
    private String appid;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void mustGetWeathers() throws Exception {
        JSONObject jsonObject = weatherServiceClient.getWeather(id, appid, 45.0, 25.0);
        List<Weather> weathers = WeatherUtil.getWeathers();

        when(weatherServiceClientMock.getWeather(null, null, 45.0, 25.0)).thenReturn(jsonObject);
        when(weatherParser.parse(jsonObject)).thenReturn(weathers);

        List<Weather> current = weatherService.getWeather(45.0, 25.0);
        assertEquals(5, current.size());
    }

}
