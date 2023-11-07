package com.example.astondevtest.controller;

import com.example.astondevtest.dto.MoneyTransferDto;
import com.example.astondevtest.exceptions.BalanceException;
import com.example.astondevtest.exceptions.EntityNotFoundException;
import com.example.astondevtest.exceptions.IncorrectPinCodeException;
import com.example.astondevtest.mapper.MoneyTransferMapper;
import com.example.astondevtest.service.interfaces.MoneyTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MoneyTransfer controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class MoneyTransferController {

    private final MoneyTransferService service;

    private final MoneyTransferMapper mapper;
    @Operation(summary = "make deposit to AccountNumber")
    @PostMapping("/deposit")
    public ResponseEntity<HttpStatus> deposit(@RequestBody MoneyTransferDto dto) throws EntityNotFoundException {
        service.deposit(mapper.toEntity(dto));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Operation(summary = "make withdraw from AccountNumber")
    @PostMapping("/withdraw")
    public ResponseEntity<HttpStatus> withdraw(@RequestBody MoneyTransferDto dto) throws EntityNotFoundException, IncorrectPinCodeException, BalanceException {
        service.withdraw(mapper.toEntity(dto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "make transfer from AccountNumber -> to AccountNumber")
    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> transfer(@RequestBody MoneyTransferDto dto) throws EntityNotFoundException, IncorrectPinCodeException, BalanceException {
        service.transfer(mapper.toEntity(dto));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
