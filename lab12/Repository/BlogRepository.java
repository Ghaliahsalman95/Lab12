package com.example.lab12.Repository;

import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    List<Blog> findAllByUser(MyUser user);
    Blog findBlogById(Integer id);
}
