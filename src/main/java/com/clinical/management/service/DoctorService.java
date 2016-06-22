package com.clinical.management.service;

import com.clinical.management.model.Patient;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private static final String patientsDatabase = "/Users/daniellungu/Documents/Workspace/Java/Clinical-Management/src/main/resources/patients.json";

    public static List<Patient> getAllDoctorPatients(int doctorID) {
        List<Patient> patients = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {

            // Convert JSON string from file to Object
            List<Patient> p = mapper.readValue(new File(patientsDatabase), new TypeReference<List<Patient>>() {
            });

            for (Patient patient : p)
                if (patient.getDoctorID() == doctorID)
                    patients.add(patient);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
    }

}
