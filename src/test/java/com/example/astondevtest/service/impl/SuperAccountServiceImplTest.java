package com.example.astondevtest.service.impl;

import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.exceptions.IncorrectPinCodeException;
import com.example.astondevtest.model.SuperAccountEntity;
import com.example.astondevtest.repository.SuperAccountRepository;
import com.example.astondevtest.service.interfaces.SuperAccountService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SuperAccountServiceImplTest {

    SuperAccountRepository repoMock
            = Mockito.mock(SuperAccountRepository.class);

    SuperAccountService service = new SuperAccountServiceImpl(repoMock);

    @Test
    @DisplayName("find by id, positive test")
    void findByIdPositiveTest() {
        when(repoMock.findById(anyLong())).thenReturn(Optional.ofNullable(new SuperAccountEntity()));
        assertThat(service.findById(1l)).isInstanceOf(SuperAccountEntity.class);
    }

    @Test
    @DisplayName("find by non existing id, negative test")
    void findByNonExistingIdNegativeTest() {
        when(repoMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.findById(10l));
    }
    @Test
    @DisplayName("saving, positive test")
    void savePositiveTest() {
        SuperAccountEntity entity = new SuperAccountEntity();
        entity.setAccountNumbers(new ArrayList<>());
        entity.setPinCode("1234");
        when(repoMock.save(any(SuperAccountEntity.class))).thenReturn(entity);
        assertThat(service.save(entity)).isEqualTo(entity);
    }

    @Test
    @DisplayName("incorrect pin-code saving, negative test")
    void savingWithIncorrectPincode() {
        SuperAccountEntity entity = new SuperAccountEntity();
        entity.setPinCode("12345");
        when(repoMock.save(any(SuperAccountEntity.class))).thenReturn(entity);
        assertThrows(IncorrectPinCodeException.class, () -> service.save(entity));
    }

    @Test
    void update() {
    }

    @Test
    void findAllById() {
    }
}