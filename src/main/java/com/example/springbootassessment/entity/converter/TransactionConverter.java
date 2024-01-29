package com.example.springbootassessment.entity.converter;

import com.example.springbootassessment.entity.Transaction;
import com.example.springbootassessment.entity.User;
import com.example.springbootassessment.entity.dtos.reqDtos.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public Transaction toEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setType(transactionDto.getTransactionType());
        transaction.setUser(toUserEntity(transactionDto));
        return transaction;
    }

    private User toUserEntity(TransactionDto transactionDto) {
        User user = new User();
        user.setId(transactionDto.getUserId());
        return user;
    }
}
