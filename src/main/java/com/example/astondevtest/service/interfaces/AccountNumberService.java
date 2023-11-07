package com.example.astondevtest.service.interfaces;

import com.example.astondevtest.model.AccountNumberEntity;

import java.util.List;

public interface AccountNumberService {

    AccountNumberEntity findById(Long id);

    AccountNumberEntity save(Long id);


}
