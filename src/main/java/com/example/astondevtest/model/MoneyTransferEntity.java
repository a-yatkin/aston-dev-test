package com.example.astondevtest.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "money_transfer", schema = "aston_test")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoneyTransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String senderNumber;

    String receiverNumber;

    String operationType;

    String inputPincode;

    Boolean done;

    BigDecimal value;
}
