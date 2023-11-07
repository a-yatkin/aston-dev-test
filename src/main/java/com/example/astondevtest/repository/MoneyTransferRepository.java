package com.example.astondevtest.repository;

import com.example.astondevtest.model.MoneyTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyTransferRepository extends JpaRepository<MoneyTransferEntity, Long> {
    @Query("SELECT me FROM MoneyTransferEntity me GROUP BY me.id" +
            " having me.receiverNumber = :number or me.senderNumber = :number ")
    List<MoneyTransferEntity> findAllByNumber(String number);

}
