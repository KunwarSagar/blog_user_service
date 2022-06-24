package com.blogapp.com.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    Collection<User> users;

    public int size(){
        return this.users.size();
    }

}
