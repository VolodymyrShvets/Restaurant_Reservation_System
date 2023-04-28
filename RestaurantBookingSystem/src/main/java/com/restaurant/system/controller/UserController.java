package com.restaurant.system.controller;

import com.restaurant.system.model.User;
import com.restaurant.system.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }
}
