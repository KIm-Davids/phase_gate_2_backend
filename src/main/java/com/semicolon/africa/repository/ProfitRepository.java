package com.semicolon.africa.repository;

import com.semicolon.africa.models.Profit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfitRepository extends MongoRepository<Profit, String> {
}
