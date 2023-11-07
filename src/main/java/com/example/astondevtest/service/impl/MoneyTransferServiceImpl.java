package com.example.astondevtest.service.impl;

import com.example.astondevtest.exceptions.BalanceException;
import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.exceptions.IncorrectPinCodeException;
import com.example.astondevtest.model.MoneyTransferEntity;
import com.example.astondevtest.repository.AccountNumberRepository;
import com.example.astondevtest.repository.MoneyTransferRepository;
import com.example.astondevtest.repository.SuperAccountRepository;
import com.example.astondevtest.service.interfaces.MoneyTransferService;
import com.example.astondevtest.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MoneyTransferServiceImpl implements MoneyTransferService {

    private static final String NUMBER_NF_PREFIX = "Can't found AccountNumber = ";
    private final AccountNumberRepository numberRepository;
    private final SuperAccountRepository accountRepository;
    private final MoneyTransferRepository transferRepository;

    @Override
    @Transactional
    public void deposit(MoneyTransferEntity transferEntity) throws EntityNotFoundException {
        var numberEntity = numberRepository.findFirstByAccountNumber(
                                                    transferEntity.getReceiverNumber())
                                                .orElseThrow(() -> new EntityNotFoundException(NUMBER_NF_PREFIX + transferEntity.getReceiverNumber()));
        numberEntity.setBalance(numberEntity.getBalance().add(transferEntity.getValue()));
        numberRepository.save(numberEntity);
        transferEntity.setDone(true);
        transferEntity.setOperationType("DEPOSIT");
        transferEntity.setSenderNumber("BANKOMAT");
        transferRepository.save(transferEntity);
    }

    @Override
    @Transactional
    public void withdraw(MoneyTransferEntity transferEntity) throws EntityNotFoundException, IncorrectPinCodeException, BalanceException {

        var numberEntity = numberRepository.findFirstByAccountNumber(transferEntity.getSenderNumber())
                .orElseThrow(() -> new EntityNotFoundException(NUMBER_NF_PREFIX + transferEntity.getSenderNumber()));
        var superEntity = accountRepository.findById(numberEntity.getSuperAccount().getId())
                .orElseThrow(() -> new EntityNotFoundException("Super Not Found"));

        Validator.moneyTransferPincodeCheck(transferEntity.getInputPincode(), superEntity.getPinCode());
        Validator.checkSenderAccountNumberBalance(numberEntity.getBalance(), transferEntity.getValue());


        numberEntity.setBalance(numberEntity.getBalance().subtract(transferEntity.getValue()));
        numberRepository.save(numberEntity);

        transferEntity.setDone(true);
        transferEntity.setReceiverNumber("BANKOMAT");
        transferEntity.setOperationType("WITHDRAW");
        transferRepository.save(transferEntity);

    }

    @Override
    @Transactional
    public void transfer(MoneyTransferEntity transferEntity) {

        var numberEntitySender = numberRepository.findFirstByAccountNumber(transferEntity.getSenderNumber())
                .orElseThrow(() -> new EntityNotFoundException("SenderAccountNumber not found = " + transferEntity.getSenderNumber()));
        var numberEntityReceiver = numberRepository.findFirstByAccountNumber(transferEntity.getReceiverNumber())
                .orElseThrow(() -> new EntityNotFoundException("ReceiverAccountNumber not found = " + transferEntity.getSenderNumber()));

        Validator.moneyTransferPincodeCheck(transferEntity.getInputPincode(), numberEntitySender.getSuperAccount().getPinCode());
        Validator.checkSenderAccountNumberBalance(numberEntitySender.getBalance(), transferEntity.getValue());

        numberEntitySender.setBalance(numberEntitySender.getBalance().subtract(transferEntity.getValue()));
        numberEntityReceiver.setBalance(numberEntityReceiver.getBalance().add(transferEntity.getValue()));
        numberRepository.save(numberEntitySender);
        numberRepository.save(numberEntityReceiver);

        transferEntity.setOperationType("TRANSFER");
        transferEntity.setDone(true);
        transferRepository.save(transferEntity);

    }
}
