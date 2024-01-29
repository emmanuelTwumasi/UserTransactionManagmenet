package com.example.springbootassessment.entity;

import com.example.springbootassessment.entity.dtos.reqDtos.TransactionType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
@Table(name = "transaction")
public class Transaction extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "transaction_type")
	@Enumerated(EnumType.STRING)
	private TransactionType type;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}

