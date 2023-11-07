package com.example.astondevtest.service.impl;

import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.exceptions.IncorrectPinCodeException;
import com.example.astondevtest.model.AccountNumberEntity;
import com.example.astondevtest.model.SuperAccountEntity;
import com.example.astondevtest.repository.SuperAccountRepository;
import com.example.astondevtest.service.interfaces.SuperAccountService;
import com.example.astondevtest.util.AccountNumberAutoGenerator;
import com.example.astondevtest.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperAccountServiceImpl implements SuperAccountService {

    private static final String MESSAGE_PREFIX = "This SuperAccount ID doesn't exist = ";

    private final SuperAccountRepository repository;

    //() -> new EntityNotFoundException(MESSAGE_PREFIX + id)

    @Override
    public SuperAccountEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE_PREFIX + id));
    }

    @Override
    @Transactional
    public SuperAccountEntity save(SuperAccountEntity superAccountEntity) throws IncorrectPinCodeException {

        Validator.checkRegistrationPincode(superAccountEntity.getPinCode());

        AccountNumberEntity accountNumber = new AccountNumberEntity();
        accountNumber.setAccountNumber(AccountNumberAutoGenerator.generate());
        accountNumber.setBalance(BigDecimal.ZERO);
        accountNumber.setSuperAccount(superAccountEntity);

        List<AccountNumberEntity> numbers = superAccountEntity.getAccountNumbers();
        numbers.add(accountNumber);

        superAccountEntity.setAccountNumbers(numbers);
        return repository.save(superAccountEntity);
    }

}
