package com.clinical.management.doctor.service;

import com.clinical.management.doctor.DoctorApplication;
import com.clinical.management.doctor.client.WeatherServiceClient;
import com.clinical.management.doctor.domain.Doctor;
import com.clinical.management.doctor.domain.Weather;
import com.clinical.management.doctor.parser.WeatherParse;
import com.clinical.management.doctor.repository.DoctorRepository;
import com.clinical.management.doctor.util.DoctorUtil;
import com.clinical.management.doctor.util.JsonUtil;
import com.clinical.management.doctor.util.WeatherUtil;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DoctorApplication.class)
public class WeatherServiceTest {

    @Mock
    private WeatherServiceClient weatherServiceClient;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private WeatherParse weatherParser;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void mustGetWeathers() throws Exception {
        Doctor doctor = DoctorUtil.getDoctor();
        JSONObject jsonObject = JsonUtil.getWeatherJson();
        List<Weather> weathers = WeatherUtil.getWeathers();

        when(doctorRepository.findDoctorByEmail(doctor.getEmail())).thenReturn(doctor);
        when(weatherServiceClient.getWeather(null, null, doctor.getLatitude(), doctor.getLongitude())).thenReturn(jsonObject);
        when(weatherParser.parse(jsonObject)).thenReturn(weathers);

        List<Weather> current = weatherService.getWeather(doctor.getEmail());
        assertEquals(5, current.size());
    }

}
