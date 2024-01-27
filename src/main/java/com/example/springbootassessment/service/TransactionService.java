package com.example.springbootassessment.service;

import com.example.springbootassessment.entity.Transaction;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);

    @Cacheable(value = "transactions", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    Page<Transaction> getAllTransactions(Pageable pageable);
}
