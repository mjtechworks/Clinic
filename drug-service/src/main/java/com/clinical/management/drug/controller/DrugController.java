package com.clinical.management.drug.controller;


import com.clinical.management.drug.domain.Drug;
import com.clinical.management.drug.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DrugController {
    private DrugRepository drugRepository;

    @Autowired
    public DrugController(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Drug create(@RequestBody Drug drug) {
        return drugRepository.save(drug);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{drugId}")
    public Drug get(@PathVariable String drugId) {
        return drugRepository.findOne(drugId);
    }
}
