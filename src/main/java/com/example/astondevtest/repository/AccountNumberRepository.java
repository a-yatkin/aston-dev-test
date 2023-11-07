package com.example.astondevtest.repository;

import com.example.astondevtest.model.AccountNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountNumberRepository extends JpaRepository<AccountNumberEntity, Long> {

    Optional<AccountNumberEntity> findFirstByAccountNumber(String accountNumber);

}
