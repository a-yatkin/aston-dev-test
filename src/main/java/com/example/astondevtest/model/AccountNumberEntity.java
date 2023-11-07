package com.example.astondevtest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_number", schema = "aston_test")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String accountNumber;

    BigDecimal balance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "super_id")
    SuperAccountEntity superAccount;

}
