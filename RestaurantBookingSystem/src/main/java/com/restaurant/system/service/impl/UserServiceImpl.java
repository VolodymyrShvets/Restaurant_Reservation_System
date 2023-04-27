package com.restaurant.system.service.impl;

import com.restaurant.system.model.User;
import com.restaurant.system.repository.UserRepository;
import com.restaurant.system.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Override
    public User registerNewUser(User user) {
        log.info("Registering new User with email {}", user.getEmail());

        if (repository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException(); // TODO create UserAlreadyExists exception

        User newUser = repository.save(user);

        log.info("User with email {} successfully created", user.getEmail());
        return newUser;
    }

    @Override
    public User getUser(String email) {
        log.info("Getting User with email {}", email);

        User user = repository.findByEmail(email);
        if (user == null)
            throw new IllegalArgumentException(); // TODO create UserNotFound exception

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all Users");
        return repository.findAll();
    }

    @Override
    public User updateUser(User user) {
        log.info("Updating User with email {}", user.getEmail());

        User persistedUser = repository.findByEmail(user.getEmail());
        if (persistedUser == null)
            throw new IllegalArgumentException(); // TODO create UserNotFound exception

        //persistedUser = populateWithPresentFields(); // TODO create method
        User storedUser = repository.save(persistedUser);

        log.info("User with email {} successfully updated", storedUser.getEmail());
        return storedUser;
    }

    @Override
    public void deleteUser(String email) {
        log.info("Deleting the User with email {}", email);
        repository.deleteByEmail(email);
    }
}
