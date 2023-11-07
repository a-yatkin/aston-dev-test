package com.example.astondevtest.controller;

import com.example.astondevtest.mapper.MoneyTransferMapper;
import com.example.astondevtest.service.interfaces.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@Tag(name = "Audit controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {

    private final AuditService service;

    private final MoneyTransferMapper transferMapper;

    @Operation(summary = "get information about all AccountNumbers")
    @GetMapping("/all")
    public ResponseEntity<?> allTest() {
        return ResponseEntity.ok(
                service.findAll().entrySet().stream()
                        .map(e -> {
                            Map<String, String> map = new HashMap<>();
                            map.put("accountNumber", e.getKey().getAccountNumber());
                            map.put("balance", String.valueOf(e.getKey().getBalance()));
                            map.put("superName", e.getValue());
                            return map;
                        }).collect(Collectors.toList())
        );
    }
    @Operation(summary = "get MoneyTransfer history of AccountNumber")
    @GetMapping("/{id}")
    public ResponseEntity<?> oneNumber(@PathVariable Long id) {
        return ResponseEntity.ok(service.findHistoryOfNumberById(id).stream()
                .map(transferMapper::toDto).toList());
    }
}

