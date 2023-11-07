package com.example.astondevtest.dto;

import com.example.astondevtest.model.AccountNumberEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuperAccountDto {
    Long id;
    String name;
    String pinCode;
    List<AccountNumberEntity> accountNumbers;
}