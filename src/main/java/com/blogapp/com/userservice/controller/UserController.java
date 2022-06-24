package com.blogapp.com.userservice.controller;

import com.blogapp.com.userservice.exceptions.UserNotFoundException;
import com.blogapp.com.userservice.model.User;
import com.blogapp.com.userservice.model.Users;
import com.blogapp.com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers(){
        Users users = userService.getAll();
        if(users.size() > 0){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new UserNotFoundException("Users not found").getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        User user = userService.getUser(userId);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new UserNotFoundException("User by id "+userId+" not found").getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
