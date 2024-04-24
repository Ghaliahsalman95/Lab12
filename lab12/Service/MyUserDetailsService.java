package com.example.lab12.Service;

import com.example.lab12.ApiResponse.APIException;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Repository.MyUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = myUserRepository.findUserByUsername(username);
        if (user == null) throw new APIException("Wrong username or password");
        return user;
    }






}