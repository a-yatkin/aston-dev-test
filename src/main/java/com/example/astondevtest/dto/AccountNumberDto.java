package com.example.astondevtest.dto;

import com.example.astondevtest.model.SuperAccountEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountNumberDto {
    Long id;
    String accountNumber;
    BigDecimal balance;
    SuperAccountEntity superAccount;
}
