package com.example.lab12.Service;

import com.example.lab12.ApiResponse.APIException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Repository.BlogRepository;
import com.example.lab12.Repository.MyUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {


    private final MyUserRepository myUserRepository;

    public void register(MyUser user) {

        String hash_password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash_password);//never save password without decrypt
        myUserRepository.save(user);

    }

    public List<MyUser> getAll() {
        if (myUserRepository.findAll().isEmpty())
            throw new APIException("empty list");
        else return myUserRepository.findAll();
    }


    public void delete(String username) {
        MyUser user = myUserRepository.findUserByUsername(username);
        if (user == null) {
            throw new APIException("Not found user");
        }

    }


    public void update(String username, MyUser usernew) {
        MyUser user = myUserRepository.findUserByUsername(username);
        if (user == null) {
            throw new APIException("USER NOT Found");
        }
        user.setPassword(usernew.getPassword());
        user.setUsername(usernew.getUsername());
        myUserRepository.save(user);

    }


}