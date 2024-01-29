package com.example.springbootassessment.controller;

import com.example.springbootassessment.entity.Transaction;
import com.example.springbootassessment.entity.dtos.reqDtos.TransactionDto;
import com.example.springbootassessment.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Api(tags = "Transaction Management")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation("Get all transactions.")
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
    @ApiOperation("Get transaction given the transaction id.")
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @ApiOperation("Initiate a transaction.")
    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionDto transaction) {
        return transactionService.createTransaction(transaction);
    }

    @ApiOperation("Update a transaction.")
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @ApiOperation("Delete a transaction.")
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping
    public Page<Transaction> getAllTransactions(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id,asc") String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return transactionService.getAllTransactions(pageable);
    }
}

