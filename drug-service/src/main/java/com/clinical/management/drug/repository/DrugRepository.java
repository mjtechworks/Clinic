package com.clinical.management.drug.repository;

import com.clinical.management.drug.domain.Drug;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends MongoRepository<Drug, String> {
}

