package com.example.astondevtest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoneyTransferDto {
    Long id;

    String senderNumber;

    String receiverNumber;

    String operationType;

    String inputPincode;

    Boolean done;

    BigDecimal value;
}
