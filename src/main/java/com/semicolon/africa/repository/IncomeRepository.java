package com.semicolon.africa.repository;

import com.semicolon.africa.constants.IncomeType;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.models.Income;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;


public interface IncomeRepository extends MongoRepository<Income, String> {
    Income findByIncomeType(IncomeType request);

}
