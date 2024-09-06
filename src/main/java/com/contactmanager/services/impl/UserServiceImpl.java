package com.contactmanager.services.impl;

import java.util.List;
import java.util.Optional;

import java.util.UUID;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactmanager.entities.User;
import com.contactmanager.helpers.ResourceNotFoundException;
import com.contactmanager.repositories.UserRepo;
import com.contactmanager.services.UserService;

@Service
public class UserServiceImpl implements UserService{


    // Constructor injection
    private final UserRepo userRepo;
    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // user id need to be generate automatically
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException());
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setPassword(user.getPassword());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
         userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String id) {
        User user2 = userRepo.findById(id).orElse(null);
        if(user2==null) return false;
        return true;    
    }

    @Override
    public boolean isUserExistByEmail(String email) {
       User user2 = userRepo.findByEmail(email).orElse(null);
       return user2!=null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();    
    }


}
