package com.blogapp.com.userservice.service;

import com.blogapp.com.userservice.model.User;
import com.blogapp.com.userservice.model.Users;

public interface UserService {
    Users getAll();
    User getUser(Long user_id);
    User save(User user);
    User update(User user, Long user_id);
    Boolean delete(Long user_id);
}
