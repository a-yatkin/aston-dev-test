package com.example.astondevtest.service.impl;

import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.model.AccountNumberEntity;
import com.example.astondevtest.model.SuperAccountEntity;
import com.example.astondevtest.repository.AccountNumberRepository;
import com.example.astondevtest.repository.SuperAccountRepository;
import com.example.astondevtest.service.interfaces.AccountNumberService;
import com.example.astondevtest.util.AccountNumberAutoGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountNumberServiceImpl implements AccountNumberService {

    private static final String MESSAGE_PREFIX = "This AccountNumber ID doesn't exist = ";

    private final AccountNumberRepository repository;

    private final SuperAccountRepository superAccountRepository;

    //() -> new EntityNotFoundException(MESSAGE_PREFIX + id)
    @Override
    public AccountNumberEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MESSAGE_PREFIX + id));
    }

    @Override
    @Transactional
    public AccountNumberEntity save(Long id) {

        SuperAccountEntity superAccount = superAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SuperAccount not exist ID = " + id));
        //List<AccountNumberEntity> oldList = superAccount.getAccountNumbers();

        AccountNumberEntity accountNumber = new AccountNumberEntity();
        accountNumber.setAccountNumber(AccountNumberAutoGenerator.generate());
        accountNumber.setBalance(BigDecimal.ZERO);
        accountNumber.setSuperAccount(superAccount);
        /*
        oldList.add(accountNumber);
        superAccount.setAccountNumbers(oldList);

        superAccountRepository.save(superAccount);
        */
        return repository.save(accountNumber);
    }

}
