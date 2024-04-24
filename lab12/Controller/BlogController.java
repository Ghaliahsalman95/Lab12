package com.example.lab12.Controller;


import com.example.lab12.ApiResponse.APIResponse;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Service.BlogService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService ;
    //@AuthenticationPrincipal MyUser user   //mean login check is login or not

    @GetMapping("/get-all-blogs")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAll(myUser.getId()));
    }
    @GetMapping("/get-all-blogs/{username}")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser myUser,@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAll(username));
    }


    @PostMapping("/add")
public ResponseEntity addBlog(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Blog blog){
        blogService.add(user.getId(),blog);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Blog added Successfully"));
}
@PutMapping("/update/{blogId}")
public ResponseEntity update(@AuthenticationPrincipal MyUser user, @PathVariable Integer blogId,@RequestBody @Valid Blog blog){
        blogService.update(user.getId(),blogId,blog);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Blog update Successfully"));
}
@DeleteMapping("/delete/{blogId}")
public ResponseEntity delete(@AuthenticationPrincipal MyUser user,@PathVariable Integer blogId){
   blogService.delete(user.getId(),blogId);

    return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Blog deleted Successfully"));

}


}