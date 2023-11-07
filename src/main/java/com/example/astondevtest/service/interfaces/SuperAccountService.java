package com.example.astondevtest.service.interfaces;

import com.example.astondevtest.model.SuperAccountEntity;

import java.util.List;

public interface SuperAccountService {

    SuperAccountEntity findById(Long id);

    SuperAccountEntity save(SuperAccountEntity superAccountEntity);

}
