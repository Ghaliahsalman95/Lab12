package com.example.lab12.Repository;

import com.example.lab12.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser findUserByUsername(String username);
    MyUser findUserById(Integer id);
}
