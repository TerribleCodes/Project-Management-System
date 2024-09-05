package com.pms.user.service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestController;


@RequestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
     public ResponseEntity<User>getUserProfile(@RequestHeader("Authorization") String jwt){

         User user=userService.getUserProfile(jwt);
         return new ResponseEntity<>(user, HttpStatus.OK)
    }

}
