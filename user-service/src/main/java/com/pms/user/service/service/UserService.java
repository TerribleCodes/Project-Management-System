package com.pms.user.service.service;

import com.pms.user.service.modal.User;

import java.util.List;

public interface UserService {

    public User getuserProfile(String jwt);

    public List<User> getAllUsers();
}
