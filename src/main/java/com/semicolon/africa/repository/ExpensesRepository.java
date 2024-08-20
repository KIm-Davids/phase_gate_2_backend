package com.semicolon.africa.repository;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.models.Profit;
import com.semicolon.africa.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends MongoRepository<Expenses, String> {

    Expenses findExpensesByExpenseType(ExpenseType type);
//    List<Expenses> findExpensesByTotalExpenses(ExpenseRequest request);


}
