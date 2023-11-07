package com.example.astondevtest.repository;

import com.example.astondevtest.model.SuperAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAccountRepository extends JpaRepository<SuperAccountEntity, Long> {

}
