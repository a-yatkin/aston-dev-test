package com.example.astondevtest.service.impl;

import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.model.AccountNumberEntity;
import com.example.astondevtest.model.MoneyTransferEntity;
import com.example.astondevtest.repository.AccountNumberRepository;
import com.example.astondevtest.repository.MoneyTransferRepository;
import com.example.astondevtest.service.interfaces.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AccountNumberRepository numberRepository;
    private final MoneyTransferRepository transferRepository;


    @Override
    public Map<AccountNumberEntity, String> findAll() {
        return numberRepository.findAll()
                .stream().collect(
                        Collectors.toMap(k -> k, v -> v.getSuperAccount().getName()));
    }

    @Override
    public List<MoneyTransferEntity> findHistoryOfNumberById(Long id) {
        var numberEntity = numberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountNumber not exist ID = " + id));
        return transferRepository.findAllByNumber(numberEntity.getAccountNumber());
    }


}
