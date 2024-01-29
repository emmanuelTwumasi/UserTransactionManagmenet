package com.example.springbootassessment.service.serviceImpl;

import com.example.springbootassessment.entity.Transaction;
import com.example.springbootassessment.entity.converter.TransactionConverter;
import com.example.springbootassessment.entity.dtos.reqDtos.TransactionDto;
import com.example.springbootassessment.exception.TransactionNotFoundException;
import com.example.springbootassessment.repository.TransactionRepository;
import com.example.springbootassessment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "transactions", key = "#id")
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    @Transactional
    @CachePut(value = "transactions", key = "#result.id")
    public Transaction createTransaction(TransactionDto info) {
        Transaction transaction = transactionConverter.toEntity(info);
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    @CachePut(value = "transactions", key = "#id")
    public Transaction updateTransaction(Long id, TransactionDto info) {
        if (!transactionRepository.existsById(id)) {
            throw new TransactionNotFoundException("Transaction not found with id: " + id);
        }
        Transaction transaction = transactionConverter.toEntity(info);
        transaction.setTransactionId(id);
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    @CacheEvict(value = "transactions", key = "#id")
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new TransactionNotFoundException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "transactions", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

}

