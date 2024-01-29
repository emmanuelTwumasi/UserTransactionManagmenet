package com.example.springbootassessment.service;

import com.example.springbootassessment.entity.Transaction;
import com.example.springbootassessment.entity.dtos.reqDtos.TransactionDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    Transaction createTransaction(TransactionDto transaction);
    Transaction updateTransaction(Long id, TransactionDto transaction);
    void deleteTransaction(Long id);
    Page<Transaction> getAllTransactions(Pageable pageable);
}

