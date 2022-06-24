package com.blogapp.com.userservice.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String s) {
        super(s);
    }
}
