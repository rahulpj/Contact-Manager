package com.contactmanager.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contactmanager.repositories.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

    UserRepo userRepo;

    @Autowired
    public SecurityCustomUserDetailService(UserRepo userRepo){
        this.userRepo=userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return  userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user Not found with email: "+username));
    }

}
