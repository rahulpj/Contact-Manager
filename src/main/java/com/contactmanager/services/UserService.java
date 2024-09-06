package com.contactmanager.services;

import java.util.Optional;



import java.util.List;
import com.contactmanager.entities.User;


public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String id);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();

    
}
