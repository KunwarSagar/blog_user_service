package com.blogapp.com.userservice.serviceImpl;

import com.blogapp.com.userservice.model.User;
import com.blogapp.com.userservice.model.Users;
import com.blogapp.com.userservice.repository.UserRepository;
import com.blogapp.com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Users getAll() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, Long user_id) {
        return null;
    }

    @Override
    public Boolean delete(Long user_id) {
        return null;
    }
}
