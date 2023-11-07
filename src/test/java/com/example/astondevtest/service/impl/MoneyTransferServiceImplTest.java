package com.example.astondevtest.service.impl;

import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.exceptions.IncorrectPinCodeException;
import com.example.astondevtest.model.AccountNumberEntity;
import com.example.astondevtest.model.MoneyTransferEntity;
import com.example.astondevtest.model.SuperAccountEntity;
import com.example.astondevtest.repository.AccountNumberRepository;
import com.example.astondevtest.repository.MoneyTransferRepository;
import com.example.astondevtest.repository.SuperAccountRepository;
import com.example.astondevtest.service.interfaces.MoneyTransferService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MoneyTransferServiceImplTest {

    MoneyTransferRepository tranRepoMock = Mockito.mock(MoneyTransferRepository.class);

    AccountNumberRepository numRepoMock = Mockito.mock(AccountNumberRepository.class);

    SuperAccountRepository supRepoMock = Mockito.mock(SuperAccountRepository.class);

    MoneyTransferService service = new MoneyTransferServiceImpl(numRepoMock, supRepoMock, tranRepoMock);
    @Test
    @DisplayName("deposit, positive test")
    void depositPositiveTest() {
        var number = new AccountNumberEntity();
        number.setBalance(BigDecimal.valueOf(100));
        number.setAccountNumber("123123123123");
        var transfer = new MoneyTransferEntity();
        transfer.setValue(BigDecimal.valueOf(100));
        transfer.setReceiverNumber("123123123123");
        when(numRepoMock.findFirstByAccountNumber(any(String.class))).thenReturn(Optional.of(number));
        when(numRepoMock.save(any(AccountNumberEntity.class))).thenReturn(number);

        service.deposit(transfer);

        assertEquals(BigDecimal.valueOf(200), number.getBalance());
        assertEquals(true, transfer.getDone());
        assertEquals("BANKOMAT", transfer.getSenderNumber());
        assertEquals("DEPOSIT", transfer.getOperationType());
    }

    @Test
    @DisplayName("deposit on non existing number, negative test")
    void depositOnNonExistingNumberNegativeTest() {
        var transfer = new MoneyTransferEntity();
        transfer.setReceiverNumber("123123123123");
        when(numRepoMock.findFirstByAccountNumber(any(String.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.deposit(transfer));
    }

    @Test
    @DisplayName("withdraw, positive test")
    void withdrawPositiveTest() {
        var sup = new SuperAccountEntity();
        sup.setPinCode("1234");

        var number = new AccountNumberEntity();
        number.setAccountNumber("123123123123");
        number.setBalance(BigDecimal.valueOf(200));
        number.setSuperAccount(sup);

        var transfer = new MoneyTransferEntity();
        transfer.setInputPincode("1234");
        transfer.setSenderNumber("123123123123");
        transfer.setValue(BigDecimal.valueOf(100));
        when(supRepoMock.findById(number.getSuperAccount().getId())).thenReturn(Optional.ofNullable(sup));
        when(numRepoMock.findFirstByAccountNumber(any(String.class))).thenReturn(Optional.ofNullable(number));
        when(numRepoMock.save(any(AccountNumberEntity.class))).thenReturn(number);
        service.withdraw(transfer);

        assertEquals(BigDecimal.valueOf(100), number.getBalance());
        assertEquals(true, transfer.getDone());
        assertEquals("BANKOMAT", transfer.getReceiverNumber());
        assertEquals("WITHDRAW", transfer.getOperationType());

    }

    @Test
    @DisplayName("withdraw with incorrect pin-code, negative test")
    void withdrawIncorrectPinCodeNegativeTest() {
        var sup = new SuperAccountEntity();
        sup.setPinCode("1234");

        var number = new AccountNumberEntity();
        number.setSuperAccount(sup);
        number.setAccountNumber("123123123123");

        var transfer = new MoneyTransferEntity();
        transfer.setInputPincode("1222");
        transfer.setSenderNumber("123123123123");

        when(supRepoMock.findById(number.getSuperAccount().getId())).thenReturn(Optional.ofNullable(sup));
        when(numRepoMock.findFirstByAccountNumber(any(String.class))).thenReturn(Optional.ofNullable(number));

        assertThrows(IncorrectPinCodeException.class, () -> service.withdraw(transfer));
    }

    @Test
    void transfer() {
    }
}