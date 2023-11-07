package com.example.astondevtest.util;

import com.example.astondevtest.model.AccountNumberEntity;
import com.example.astondevtest.model.SuperAccountEntity;
import com.example.astondevtest.repository.AccountNumberRepository;
import com.example.astondevtest.repository.SuperAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StratingInput implements CommandLineRunner {
    @Autowired
    private SuperAccountRepository accountRepository;
    @Autowired
    private AccountNumberRepository numberRepository;

    @Override
    public void run(String... args) throws Exception {
        SuperAccountEntity super1 = new SuperAccountEntity();

        AccountNumberEntity number1_1 = new AccountNumberEntity();
        number1_1.setAccountNumber("111111111111");
        number1_1.setBalance(BigDecimal.valueOf(500));


        AccountNumberEntity number1_2 = new AccountNumberEntity();
        number1_2.setAccountNumber("222222222222");
        number1_2.setBalance(BigDecimal.valueOf(500));


        List<AccountNumberEntity> numbers1 = new ArrayList<>();
        numbers1.add(number1_1);
        numbers1.add(number1_2);

        super1.setName("superTest1");
        super1.setPinCode("4444");
        super1.setAccountNumbers(numbers1);

        accountRepository.save(super1);
        number1_1.setSuperAccount(super1);
        number1_2.setSuperAccount(super1);
        numberRepository.save(number1_1);
        numberRepository.save(number1_2);

    }
}
