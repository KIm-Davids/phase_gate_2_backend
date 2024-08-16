package com.semicolon.africa.services;

import com.semicolon.africa.constants.IncomeType;
import com.semicolon.africa.dtos.request.*;
import com.semicolon.africa.dtos.response.*;
import com.semicolon.africa.exceptions.TypeNotFoundException;
import com.semicolon.africa.models.Income;
import com.semicolon.africa.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.africa.maputility.IncomeUtility.addRequestToIncome;
import static com.semicolon.africa.validation.MoneyAmountValidation.*;

@Service
public class IncomeServices implements IncomeServicesInterface{

    private final IncomeRepository repository;

    private IncomeServices(IncomeRepository repository) {
        this.repository = repository;
    }


    public IncomeResponse addIncomeToRepository(IncomeRequest request) {
        validateIncomeAmount(request);
        Income income = new Income();
        IncomeResponse response = new IncomeResponse();
        Income expensesToSave = addRequestToIncome(request, income);
        repository.save(expensesToSave);

        response.setMessage("Income added successfully");
        return response;
    }

    public List<Income> getAllIncome(){
        return repository.findAll();
    }


    public DeleteIncomeResponse deleteIncomeByIncomeType(IncomeType incomeType) {
        DeleteIncomeResponse response = new DeleteIncomeResponse();
        Income expensesToDelete = repository.findByIncomeType(incomeType);
        repository.delete(expensesToDelete);
        response.setMessage("Expense detail deleted successfully");
        return response;
    }

    public UpdateIncomeResponse updateIncomeById(UpdateIncomeRequest request) {
        validateUpdatedIncome(request);
        UpdateIncomeResponse response = new UpdateIncomeResponse();
        Income income = repository.findByIncomeType(request.getNewIncomeType());
        income.setIncomeType(request.getNewIncomeType());
        income.setTotalIncome(request.getNewTotalIncomeAmount());
        income.setLocalDateTime(request.getNewLocalDateTime());
        income.getIncomeType();
        repository.save(income);
        response.setMessage("Updated Successfully");
        return response;
    }

    private Income findById(String id){
        return repository.findById(id).orElseThrow(() -> new TypeNotFoundException("Income Type Not Found"));
    }

    public DeleteAllResponse deleteAllIncome(){
        DeleteAllResponse response = new DeleteAllResponse();
        repository.deleteAll();
        response.setMessage("All Income Deleted Successfully");
        return response;
    }


}
