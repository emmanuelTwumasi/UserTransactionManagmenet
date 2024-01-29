package com.example.springbootassessment.service;

import com.example.springbootassessment.entity.Transaction;
import com.example.springbootassessment.exception.TransactionNotFoundException;
import com.example.springbootassessment.repository.TransactionRepository;
import com.example.springbootassessment.service.serviceImpl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> transactions = transactionService.getAllTransactions();
        assertEquals(2, transactions.size());
    }


    @Test
    void testGetTransactionById_NotFound() {
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionById(id));
    }


}
