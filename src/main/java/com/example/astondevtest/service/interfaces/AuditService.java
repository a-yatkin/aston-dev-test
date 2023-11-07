package com.example.astondevtest.service.interfaces;

import com.example.astondevtest.model.AccountNumberEntity;
import com.example.astondevtest.model.MoneyTransferEntity;

import java.util.List;
import java.util.Map;

public interface AuditService {

    Map<AccountNumberEntity, String> findAll();

    List<MoneyTransferEntity> findHistoryOfNumberById(Long id);
}
