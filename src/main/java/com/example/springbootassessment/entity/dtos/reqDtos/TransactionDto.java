package com.example.springbootassessment.entity.dtos.reqDtos;

import lombok.Data;

@Data
public class TransactionDto {
    private TransactionType transactionType;
    private Long userId;
}
