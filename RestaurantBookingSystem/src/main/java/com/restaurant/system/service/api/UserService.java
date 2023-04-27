package com.restaurant.system.service.api;

import com.restaurant.system.model.User;

import java.util.List;

public interface UserService {
    User registerNewUser(User user);

    User getUser(String email);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(String email);
}
