package com.example.lab12.Controller;

import com.example.lab12.ApiResponse.APIResponse;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController@RequestMapping("/api/v1/auth")
public class AuthController {

   private final MyUserService myUserService;
@PostMapping("/register")
   public ResponseEntity register(@RequestBody @Valid MyUser user){
       myUserService.register(user);
       return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Registered Successfully"));

   }

   @GetMapping("/logout")
public ResponseEntity logout(){
    return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Log out successfully"));

}
//@GetMapping("/get-all/{admin}")
//public  ResponseEntity getAll(@PathVariable String admin){
//    return ResponseEntity.status(200).body(myUserService.getAll(admin));
//}

    @GetMapping("/get-all")
public  ResponseEntity getAll(){
    return ResponseEntity.status(200).body(myUserService.getAll());
}

///delete/{admin}/{username}   /get-all/{admin}
@DeleteMapping("/delete/")
public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser){
    myUserService.delete(myUser.getUsername());
    return ResponseEntity.status(200).body(new APIResponse("Delete successfully"));
}
@PutMapping("/update")
public ResponseEntity update(@AuthenticationPrincipal MyUser myUser,@RequestBody @Valid MyUser user)
{myUserService.update(myUser.getUsername(),user);
    return ResponseEntity.status(200).body(new APIResponse("updated successfully"));
}


}
