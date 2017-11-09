package com.clinical.management.drug.controller;


import com.clinical.management.drug.DrugApplication;
import com.clinical.management.drug.domain.Drug;
import com.clinical.management.drug.repository.DrugRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DrugApplication.class)
@WebAppConfiguration
public class DrugControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private DrugController drugController;

    @Mock
    private DrugRepository drugRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(drugController).build();
    }


    @Test
    public void getDrugById() throws Exception {
        Drug drug = new Drug();

        drug.setId("55");
        drug.setName("Test");
        drug.setQuantity(55);

        when(drugRepository.findOne(drug.getId())).thenReturn(drug);

        mockMvc.perform(get("/" + drug.getId()))
                .andExpect(jsonPath("$.name").value(drug.getName()))
                .andExpect(jsonPath("$.quantity").value(drug.getQuantity()))
                .andExpect(status().isOk());
    }

    @Test
    public void saveDrug() throws Exception {
        Drug drug = new Drug();

        drug.setId("55");
        drug.setName("Test");
        drug.setQuantity(55);

        String json = mapper.writeValueAsString(drug);

        mockMvc.perform(post("").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

}
