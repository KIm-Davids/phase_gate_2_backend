package com.semicolon.africa.services;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.UpdateExpenseRequest;
import com.semicolon.africa.dtos.response.DeleteAllResponse;
import com.semicolon.africa.dtos.response.DeleteExpenseResponse;
import com.semicolon.africa.dtos.response.ExpenseResponse;
import com.semicolon.africa.dtos.response.UpdateExpenseResponse;
import com.semicolon.africa.exceptions.TypeNotFoundException;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.repository.ExpensesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.semicolon.africa.maputility.ExpenseUtility.addRequestToExpense;
import static com.semicolon.africa.validation.MoneyAmountValidation.validateExpenseAmount;
import static com.semicolon.africa.validation.MoneyAmountValidation.validateUpdatedExpenses;

@Service
public class ExpenseServices implements ExpenseServiceInterface{

    private final ExpensesRepository repository;

    private ExpenseServices(ExpensesRepository repository) {
        this.repository = repository;
    }


    public ExpenseResponse addExpensesToRepository(ExpenseRequest request) {
        validateExpenseAmount(request);
        Expenses expenses = new Expenses();
        ExpenseResponse response = new ExpenseResponse();
        Expenses expensesToSave = addRequestToExpense(request, expenses);
        repository.save(expensesToSave);

        response.setMessage("Expense Added Successfully");
        return response;
    }

    public List<Expenses> getAllExpense(){
        return repository.findAll();
    }


    public DeleteExpenseResponse deleteExpensesByExpenseType(ExpenseType expenseType) {
        DeleteExpenseResponse response = new DeleteExpenseResponse();
        Expenses expensesToDelete = repository.findExpensesByExpenseType(expenseType);
        repository.delete(expensesToDelete);
        response.setMessage("Expense detail deleted successfully");
        return response;
    }

    public UpdateExpenseResponse updateExpensesByExpenseType(UpdateExpenseRequest request) {
        validateUpdatedExpenses(request);
        UpdateExpenseResponse response = new UpdateExpenseResponse();
        Expenses expenses = repository.findExpensesByExpenseType(request.getNewExpenseType());
        expenses.setExpenseType(request.getNewExpenseType());
        expenses.setTotalExpenses(request.getNewTotalExpenseAmount());
        expenses.setLocalDateTime(LocalDateTime.now());
        repository.save(expenses);
        response.setMessage("Updated Successfully");
        return response;
    }


    private Expenses findById(String id){
            return repository.findById(id).orElseThrow(() -> new TypeNotFoundException("Expense Type Not Found"));
    }

    public DeleteAllResponse deleteAllExpenses(){
        DeleteAllResponse response = new DeleteAllResponse();
        repository.deleteAll();
        response.setMessage("All fields have been deleted successfully");
        return response;
    }

}
