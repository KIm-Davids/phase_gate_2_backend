package com.semicolon.africa.services;

import com.semicolon.africa.constants.IncomeType;
import com.semicolon.africa.dtos.request.*;
import com.semicolon.africa.dtos.response.*;
import com.semicolon.africa.exceptions.TypeNotFoundException;
import com.semicolon.africa.models.Income;
import com.semicolon.africa.models.User;
import com.semicolon.africa.repository.IncomeRepository;
import com.semicolon.africa.repository.UserRepsoitory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.semicolon.africa.maputility.IncomeUtility.addRequestToIncome;
import static com.semicolon.africa.maputility.UserUtility.mapUserToIncome;
import static com.semicolon.africa.validation.MoneyAmountValidation.*;
import static com.semicolon.africa.validation.UserValidation.validateEmail;

@Service
public class IncomeServices implements IncomeServicesInterface{

    private final IncomeRepository repository;
    private final UserRepsoitory userRepository;

    private IncomeServices(IncomeRepository repository, UserRepsoitory userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    public IncomeResponse addIncomeToRepository(IncomeRequest request) {
        validateIncomeAmount(request);
        Income income = new Income();
        IncomeResponse response = new IncomeResponse();
        mapUserToIncome(request);

        List<Income> incomeList = new ArrayList<>();
        Income incomeToSave = addRequestToIncome(request, income);
        incomeList.add(incomeToSave);
        repository.save(incomeToSave);

        User user = userRepository.findUserByEmail(request.getEmail());
        validateEmail(user);
        user.setIncome(incomeList);
        userRepository.save(user);
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
