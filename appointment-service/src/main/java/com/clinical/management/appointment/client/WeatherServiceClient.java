package com.clinical.management.appointment.client;

import org.json.simple.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${weather.url}", name = "weather-client")
public interface WeatherServiceClient {

    @RequestMapping(method = RequestMethod.GET)
    JSONObject getWeather(@RequestParam("id") Integer id, @RequestParam("APPID") String appid, @RequestParam("lat") Double lat, @RequestParam("lon") Double lon);

}
