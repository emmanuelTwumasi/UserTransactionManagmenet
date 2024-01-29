package com.example.springbootassessment.service;

import com.example.springbootassessment.entity.Transaction;
import com.example.springbootassessment.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User transaction);
    User updateUser(Long id, User transaction);
    void deleteUser(Long id);
    Page<User> getAllUsers(Pageable pageable);
}
