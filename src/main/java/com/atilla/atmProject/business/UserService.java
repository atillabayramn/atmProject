package com.atilla.atmProject.business;

import com.atilla.atmProject.dataAccess.UserRepository;
import com.atilla.atmProject.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setBalance(newUser.getBalance());
            foundUser.setUserId(newUser.getUserId());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }


}
