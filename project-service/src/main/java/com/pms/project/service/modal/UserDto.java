package com.pms.project.service.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// user model
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    // user id
    private Long id;

    // user password
    public String password;

    // user email
    public String email;

    // user role
    public String role;

    // user fullname
    public String fullName;
}
