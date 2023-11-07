package com.example.astondevtest.controller;

import com.example.astondevtest.dto.AccountNumberDto;
import com.example.astondevtest.mapper.AccountNumberMapper;
import com.example.astondevtest.service.interfaces.AccountNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AccountNumber controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/number")
public class AccountNumberController {

    private final AccountNumberService service;

    private final AccountNumberMapper mapper;
    @Operation(summary = "get existing AccountNumber by ID")
    @GetMapping("/{id}")
    public AccountNumberDto read(@PathVariable("id") Long id) {
        return mapper.toDto(service.findById(id));
    }
    @Operation(summary = "create new AccountNumber for existing SuperAccount")
    @PostMapping("/{id}/create")
    public ResponseEntity<AccountNumberDto> create(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.toDto(
                service.save(id)));
    }

}
