package com.example.astondevtest.service.impl;

import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.model.AccountNumberEntity;
import com.example.astondevtest.repository.AccountNumberRepository;
import com.example.astondevtest.repository.SuperAccountRepository;
import com.example.astondevtest.service.interfaces.AccountNumberService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class AccountNumberServiceImplTest {

    AccountNumberRepository numRepoMock = Mockito.mock(AccountNumberRepository.class);

    SuperAccountRepository supRepoMock = Mockito.mock(SuperAccountRepository.class);

    AccountNumberService service = new AccountNumberServiceImpl(numRepoMock, supRepoMock);

    @Test
    @DisplayName("find by ID, positive test")
    void findByIdPositiveTest() {
        var number = new AccountNumberEntity();
        when(numRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(number));
        assertThat(service.findById(1L)).isInstanceOf(AccountNumberEntity.class);
    }

    @Test
    @DisplayName("find by non existing ID, negative test")
    void findNonExistingByIdNegativeTest() {
        when(numRepoMock.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.findById(23L));
    }

    /*@Test
    @DisplayName("saving, positive test")
    void savePositiveTest() {
        var sup = new SuperAccountEntity();
        sup.setId(123L);

        when(supRepoMock.findById(any(Long.class))).thenReturn(Optional.ofNullable(sup));
        var num = service.save(123L);
        when(numRepoMock.save(any(AccountNumberEntity.class))).thenReturn((num));


       // assertEquals(BigDecimal.ZERO, newNum.getBalance());
        assertEquals(12, num.getAccountNumber().length());
        assertEquals(sup, num.getSuperAccount());
    }*/
}