package com.clinical.management.doctor.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;

public class JsonUtil {

    public static JSONObject getWeatherJson() throws Exception {
        JSONParser parser = new JSONParser();
        File weatherFile = ResourceUtils.getFile("classpath:weather.json");
        return (JSONObject) parser.parse(new FileReader(weatherFile));
    }

}
