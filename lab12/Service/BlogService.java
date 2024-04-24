package com.example.lab12.Service;

import com.example.lab12.ApiResponse.APIException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Repository.BlogRepository;
import com.example.lab12.Repository.MyUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository ;
    private final MyUserRepository myUserRepository  ;

    public List<Blog> getAll(Integer userId) {
        if (blogRepository.findAllByUser(myUserRepository.findUserById(userId)).isEmpty()) throw new APIException("Empty list Blog");
        else return blogRepository.findAllByUser(myUserRepository.findUserById(userId));
    }
    public List<Blog> getAll(String userId) {
        MyUser user=myUserRepository.findUserByUsername(userId);
        if (user==null){throw new APIException("Not Found user");}
        if (blogRepository.findAllByUser(myUserRepository.findUserById(user.getId())).isEmpty()) throw new APIException("Empty list Blog");
        else return blogRepository.findAllByUser(myUserRepository.findUserById(user.getId()));
    }


    public void add(Integer MyUserId, Blog blog) {
        MyUser MyUser = myUserRepository.findUserById(MyUserId);
        blog.setUser(MyUser);
        blogRepository.save(blog);//don't miss is important
//        MyUser.getBlogSet().add(Blog);myUserRepository.save(MyUser)//        blogRepository.save(Blog);// no need after spring security

    }

    public void update(Integer myUserId, Integer BlogId, Blog newblog) {
        Blog blog = blogRepository.findBlogById(BlogId);
        if (blog==null){throw new APIException("Blog Id Not Found");}
        if (blog.getUser().getId()==myUserId) {
            blog.setTitle(newblog.getTitle());
            blog.setBody(newblog.getBody());
            blogRepository.save(blog);
        }
        else {throw new APIException("Unauthorized for you");}

    }
public void delete(Integer MyUserId, Integer blogId){

    Blog blog1 = blogRepository.findBlogById(blogId);
    if (blog1==null){throw new APIException("Blog Id Not Found");}
    if (blog1.getUser().getId()==MyUserId){
    blogRepository.delete(blog1);}
    else {throw new APIException("Unauthorized for you");}
}
}
