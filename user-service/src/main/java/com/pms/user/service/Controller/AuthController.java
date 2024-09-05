package com.pms.user.service.Controller;

import com.pms.user.service.response.AuthResponse;

public class AuthController {






    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody User user ) throws UserException {
      String email =user.getEmail();
      String password = user.getPassword();
      String fullName = user.getFullname();
      String mobile = user.getMobile();
      String role = user.getRole();
    }

    User isEmailExist = userRepository.findByEmail(email); {
    if (isEmailExist! = null)
    throw new Exception ("Email Is Already Used");
    }

    //create new user
    User CreatedUser =new User();
    createdUser.setEmail(email);
    createdUser.setFullName(fullName);
    createdUser.setRole(role);
    createdUser.setPassword(passwordEncoder.encode(password));

    User savedUser = userRepository.save(createdUser);

}
