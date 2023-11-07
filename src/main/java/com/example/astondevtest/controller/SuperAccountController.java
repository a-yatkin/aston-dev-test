package com.example.astondevtest.controller;

import com.example.astondevtest.dto.SuperAccountDto;
import com.example.astondevtest.exceptions.IncorrectPinCodeException;
import com.example.astondevtest.mapper.SuperAccountMapper;
import com.example.astondevtest.service.interfaces.SuperAccountService;
import com.example.astondevtest.util.Validator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "SuperAccount controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/super")
public class SuperAccountController {

    private final SuperAccountService service;

    private final SuperAccountMapper mapper;

    @Operation(summary = "get information about SuperAccount by ID")
    @GetMapping("/{id}")
    public SuperAccountDto read(@PathVariable("id") Long id) {
        return mapper.toDto(service.findById(id));
    }

    @Operation(summary = "create new SuperAccount with AccountNumber")
    @PostMapping("/create")
    public SuperAccountDto create(@RequestBody SuperAccountDto superAccountDto) throws IncorrectPinCodeException {
        Validator.checkRegistrationPincode(superAccountDto.getPinCode());
        return mapper.toDto(
                service.save(
                        mapper.toEntity(superAccountDto)));
    }
}
