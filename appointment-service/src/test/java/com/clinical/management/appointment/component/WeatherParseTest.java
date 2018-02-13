package com.clinical.management.appointment.component;

import com.clinical.management.appointment.AppointmentApplication;
import com.clinical.management.appointment.domain.Weather;
import com.clinical.management.appointment.util.WeatherUtil;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void shouldGetWeathers() throws Exception {
        JSONObject jsonObject = WeatherUtil.getWeatherJson();
        List<Weather> weathers = weatherParser.parse(jsonObject);

        assertNotNull(weathers);
        assertTrue(weathers.size() > 3);
    }

}
