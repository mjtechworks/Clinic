package com.clinical.management.appointment.client;

import com.clinical.management.appointment.domain.Patient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "patient-service")
public interface PatientClientService {

    @RequestMapping(method = RequestMethod.GET, value = "/patients/{patientId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Patient getAccount(@PathVariable("patientId") String accountName);

}
