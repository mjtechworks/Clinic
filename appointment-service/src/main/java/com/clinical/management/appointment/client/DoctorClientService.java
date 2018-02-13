package com.clinical.management.appointment.client;

import com.clinical.management.appointment.domain.Doctor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "doctor-service")
public interface DoctorClientService {

    @RequestMapping(method = RequestMethod.GET, value = "/doctors/find", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Doctor getDoctor(@RequestParam("email") String email);

}
