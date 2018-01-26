package com.clinical.management.doctor.client;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${weather.url}", name = "weather-client")
public interface WeatherServiceClient {

    @RequestMapping(method = RequestMethod.GET)
    JSONObject getWeather(@RequestParam("lat") Double lat, @RequestParam("lon") Double lon);

}
