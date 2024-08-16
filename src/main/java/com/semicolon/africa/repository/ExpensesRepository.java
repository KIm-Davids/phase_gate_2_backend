package com.semicolon.africa.repository;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.models.Expenses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends MongoRepository<Expenses, String> {

    Expenses findExpensesByExpenseType(ExpenseType type);

}
