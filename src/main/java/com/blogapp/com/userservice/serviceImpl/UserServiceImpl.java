package com.blogapp.com.userservice.serviceImpl;

import com.blogapp.com.userservice.model.User;
import com.blogapp.com.userservice.model.Users;
import com.blogapp.com.userservice.repository.UserRepository;
import com.blogapp.com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Get all users from the database
     * @return Users
     */
    @Override
    public Users getAll() {
        List<User> userList = userRepository.findAll();
        Users users = new Users();
        users.setUsers(userList);
        return users;
    }

    /**
     * Get user by user_id
     * @param user_id
     * @return User
     */
    @Override
    public User getUser(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new IllegalStateException(
                        "User by user id " + user_id + " not found"
                ));

        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user, Long user_id) {
        User existingUser = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id "+ user_id +" doesn't exists."
                ));
        existingUser.setValues(user);

        return userRepository.save(existingUser);
    }

    @Override
    public Boolean delete(Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if(!user.isPresent()){
            return false;
        }else{
            userRepository.delete(user.get());
            return true;
        }
    }
}
