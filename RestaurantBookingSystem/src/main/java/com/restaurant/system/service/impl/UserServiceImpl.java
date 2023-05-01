package com.restaurant.system.service.impl;

import com.restaurant.system.model.Administrator;
import com.restaurant.system.model.Customer;
import com.restaurant.system.model.User;
import com.restaurant.system.repository.AdministratorRepository;
import com.restaurant.system.repository.CustomerRepository;
import com.restaurant.system.repository.UserRepository;
import com.restaurant.system.service.api.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private CustomerRepository customerRepository;
    private AdministratorRepository administratorRepository;

    @Override
    public Customer registerNewCustomer(Customer customer) {
        log.info("Registering new Customer with email {}", customer.getEmail());

        if (customerRepository.existsByEmail(customer.getEmail()))
            throw new IllegalArgumentException(); // TODO create UserAlreadyExists exception

        Customer newCustomer = customerRepository.save(customer);

        log.info("Customer with email {} successfully created", newCustomer.getEmail());
        return newCustomer;
    }

    @Override
    public Administrator registerNewAdministrator(Administrator admin) {
        log.info("Registering new Administrator with email {}", admin.getEmail());

        if (administratorRepository.existsByEmail(admin.getEmail()))
            throw new IllegalArgumentException(); // TODO create UserAlreadyExists exception

        Administrator newAdmin = administratorRepository.save(admin);

        log.info("Administrator with email {} successfully created", newAdmin.getEmail());
        return newAdmin;
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
    @Transactional
    public void deleteUser(String email) {
        log.info("Deleting the User with email {}", email);
        repository.deleteByEmail(email);
        log.info("User with email {} successfully deleted", email);
    }
}
