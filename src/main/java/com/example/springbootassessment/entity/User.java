package com.example.springbootassessment.entity;

import jakarta.persistence.*;
import lombok.Data;


import javax.naming.Name;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Transaction> transactions;
}
