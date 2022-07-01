package com.blogapp.com.userservice.controller;

import com.blogapp.com.userservice.dto.UserDto;
import com.blogapp.com.userservice.dtoMapper.UserMapper;
import com.blogapp.com.userservice.exceptions.DeleteFailedException;
import com.blogapp.com.userservice.exceptions.UserNotFoundException;
import com.blogapp.com.userservice.exceptions.UserSaveFailedException;
import com.blogapp.com.userservice.model.User;
import com.blogapp.com.userservice.model.Users;
import com.blogapp.com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    UserMapper userMapper;

    @Autowired
    UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers(){
        System.out.println("I am here");
        Users users = userService.getAll();
        if(users.size() > 0){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new UserNotFoundException("Users not found").getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId){
        try{
            User user = userService.getUser(userId);
            if(user != null){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new UserNotFoundException("User by id "+userId+" not found").getMessage(), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto){
        User user = userMapper.UserDtoToUser(userDto);
        System.out.println(user);
        User savedUser = userService.save(user);
        if(savedUser != null){
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new UserSaveFailedException("User saved failed.").getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Long userId){
        User user = userMapper.UserDtoToUser(userDto);
        User updatedUser = userService.update(user, userId);
        if(updatedUser != null){
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new UserSaveFailedException("User update failed.").getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
        Boolean success = userService.delete(userId);
        if(success){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(new DeleteFailedException("User delete failed").getMessage(), HttpStatus.CONFLICT);
        }
    }
}
