package com.example.springbootassessment.controller;

import com.example.springbootassessment.entity.User;
import com.example.springbootassessment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    public ResponseEntity<User> getUserById(Long id){
        return  new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
    public ResponseEntity<User> createUser(User transaction){
        return new ResponseEntity<>(userService.createUser(transaction),HttpStatus.OK);
    }
    public ResponseEntity<User> updateUser(Long id, User transaction){
        return new ResponseEntity<>(userService.updateUser(id,transaction),HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> deleteUser(Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsers(pageable),HttpStatus.OK);
    }
}
