package com.semicolon.africa.services;

import com.semicolon.africa.constants.IncomeType;
import com.semicolon.africa.dtos.request.IncomeRequest;
import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.dtos.request.UpdateIncomeRequest;
import com.semicolon.africa.dtos.response.DeleteIncomeResponse;
import com.semicolon.africa.dtos.response.UpdateIncomeResponse;
import com.semicolon.africa.models.Income;
import com.semicolon.africa.repository.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IncomeServicesTest {

    @Autowired
    private IncomeRepository repository;

    @Autowired
    private IncomeServices services;

//    @BeforeEach
//    void setUp(){
//        repository.deleteAll();
//    }
//

    @Test
    void addIncomeToRepository() {
        IncomeRequest income = generateIncome();
        services.addIncomeToRepository(income);
        assertThat(repository.findAll().size()).isEqualTo(1);
    }

    @Test
    void deleteIncomeByMonth() {
        IncomeRequest income = generateIncome();
        services.addIncomeToRepository(income);
        DeleteIncomeResponse response = services.deleteIncomeByIncomeType(IncomeType.ASSETS);
        assertThat(response.getMessage()).contains("deleted");
    }

    @Test
    void updateIncomeByMonth() {
        UpdateIncomeRequest request = generateNewIncome();
        UpdateIncomeResponse response = services.updateIncomeById(request);
        assertThat(response.getMessage()).contains("Updated");
    }


    private IncomeRequest generateIncome(){
        Income expenses = new Income();
        IncomeRequest request = new IncomeRequest();
        request.setId("66b5d4d25b4c23595cb2cd46");
        request.setTotalIncomeAmount(1000);
        request.setIncomeType(IncomeType.ASSETS);

        return request;
    }


    private UpdateIncomeRequest generateNewIncome(){
        Income expenses = new Income();
        UpdateIncomeRequest request = new UpdateIncomeRequest();
        request.setId("66b5ebf7930808419915c3f5");
        request.setNewTotalIncomeAmount(2000);
        request.setNewIncomeType(IncomeType.INVESTMENTS);
        request.setNewDescription("Money well spent");
        request.setNewLocalDateTime(LocalDateTime.now());

        return request;
    }
}
