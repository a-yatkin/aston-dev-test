package com.example.astondevtest.service.interfaces;

import com.example.astondevtest.model.MoneyTransferEntity;

public interface MoneyTransferService {

    void deposit(MoneyTransferEntity entity);

    void withdraw(MoneyTransferEntity entity);

    void transfer(MoneyTransferEntity entity);
}
