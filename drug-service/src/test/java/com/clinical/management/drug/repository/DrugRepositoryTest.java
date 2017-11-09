package com.clinical.management.drug.repository;


import com.clinical.management.drug.DrugApplication;
import com.clinical.management.drug.domain.Drug;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DrugApplication.class)
public class DrugRepositoryTest {

    @Autowired
    private DrugRepository drugRepository;

    @Test
    public void findDrugById() {
        Drug drug = new Drug();

        drug.setId("55");
        drug.setName("Test");
        drug.setQuantity(55);

        drugRepository.save(drug);

        Drug found = drugRepository.findOne(drug.getId());

        assertEquals(drug.getName(), found.getName());
        assertEquals(drug.getQuantity(), found.getQuantity());
    }

}
