package com.example.springbootassessment.service.serviceImpl;

import com.example.springbootassessment.entity.Transaction;
import com.example.springbootassessment.entity.User;
import com.example.springbootassessment.exception.TransactionNotFoundException;
import com.example.springbootassessment.exception.UserNotFoundException;
import com.example.springbootassessment.repository.UserRepository;
import com.example.springbootassessment.service.UserService;
import lombok.RequiredArgsConstructor;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    @CachePut(value = "users", key = "#result.id")
    public User createUser(User transaction) {
        return userRepository.save(transaction);
    }

    @Override
    @Transactional
    @CachePut(value = "users", key = "#id")
    public User updateUser(Long id, User transaction) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        transaction.setId(id);
        return userRepository.save(transaction);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override  @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
