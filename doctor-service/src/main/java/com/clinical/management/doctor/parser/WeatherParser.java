package com.clinical.management.doctor.parser;

import com.clinical.management.doctor.domain.Weather;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherParser {

    public List<Weather> parse(JSONObject json) {
        //TODO
        return null;
    }

}
