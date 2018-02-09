package com.clinical.management.appointment.client;

import com.clinical.management.appointment.domain.Doctor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "doctor-service")
public interface DoctorClientService {

    @RequestMapping(method = RequestMethod.GET, value = "/doctors/{email}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Doctor getDoctor(@PathVariable("email") String email);

}
