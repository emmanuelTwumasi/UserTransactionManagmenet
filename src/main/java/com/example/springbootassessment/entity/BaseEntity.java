package com.example.springbootassessment.entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {


	@CreatedDate
	@Column(name="created_date")
	private LocalDateTime createdDate;


	@LastModifiedDate
	@Column(name = "last_modified_date")
	private LocalDateTime lastModifiedDate;

	@Version
	@Column(name = "version")
	private int version;

}
