package com.blogapp.com.userservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    String fullName;
    @NotNull
    String userName;
    @NotNull
    @Email
    String email;
    @NotNull
    @Size(min = 8)
    String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValues(User user) {
        if(user.getUserName() != null){
            this.setUserName(user.getUserName());
        }
        if (user.getEmail() != null){
            this.setEmail(user.getEmail());
        }
        if(user.getFullName() != null){
            this.setFullName(user.getFullName());
        }
        if(user.getPassword() != null){
            this.setPassword(user.getPassword());
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
