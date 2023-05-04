package com.restaurant.system.service.impl;

import com.restaurant.system.model.Administrator;
import com.restaurant.system.model.Customer;
import com.restaurant.system.model.User;
import com.restaurant.system.model.exception.EntityAlreadyExistsException;
import com.restaurant.system.model.exception.EntityNotFoundException;
import com.restaurant.system.repository.AdministratorRepository;
import com.restaurant.system.repository.CustomerRepository;
import com.restaurant.system.repository.UserRepository;
import com.restaurant.system.service.api.FeedbackService;
import com.restaurant.system.service.api.ReservationService;
import com.restaurant.system.service.api.UserService;
import com.restaurant.system.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    @Lazy
    private CustomerRepository customerRepository;
    @Lazy
    private AdministratorRepository administratorRepository;
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Lazy
    private FeedbackService feedbackService;
    @Lazy
    private ReservationService reservationService;

    @Override
    public Customer registerNewCustomer(Customer customer) {
        log.info("Registering new Customer with email {}", customer.getEmail());

        if (customerRepository.existsByEmail(customer.getEmail()))
            throw new EntityAlreadyExistsException(Customer.class, "email " + customer.getEmail());

        String hashedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);
        Customer newCustomer = customerRepository.save(customer);

        log.info("Customer with email {} successfully created", newCustomer.getEmail());
        return newCustomer;
    }

    @Override
    public Administrator registerNewAdministrator(Administrator admin) {
        log.info("Registering new Administrator with email {}", admin.getEmail());

        if (administratorRepository.existsByEmail(admin.getEmail()))
            throw new EntityAlreadyExistsException(Administrator.class, "email " + admin.getEmail());

        String hashedPassword = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);
        Administrator newAdmin = administratorRepository.save(admin);

        log.info("Administrator with email {} successfully created", newAdmin.getEmail());
        return newAdmin;
    }

    @Override
    public User getUser(String email) {
        log.info("Getting User with email {}", email);

        User storedUser = repository.findByEmail(email);
        if (storedUser == null)
            throw new EntityNotFoundException(User.class, "email " + email);

        User user;
        if (getDiscriminatorOfUser(storedUser).equals("customer")) {
            user = UserMapper.INSTANCE.customerToUser((Customer) storedUser);
        } else {
            user = UserMapper.INSTANCE.administratorToUser((Administrator) storedUser);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all Users");
        List<User> users = repository.findAll();
        List<User> newList = new ArrayList<>();
        for (User user : users) {
            if (getDiscriminatorOfUser(user).equals("customer")) {
                newList.add(UserMapper.INSTANCE.customerToUser((Customer) user));
            } else {
                newList.add(UserMapper.INSTANCE.administratorToUser((Administrator) user));
            }
        }
        return newList;
    }

    @Override
    public User updateUser(User user) {
        log.info("Updating User with email {}", user.getEmail());

        User persistedUser = repository.findByEmail(user.getEmail());
        if (persistedUser == null)
            throw new EntityNotFoundException(User.class, "email " + user.getEmail());

        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        if (getDiscriminatorOfUser(persistedUser).equals("customer")) {
            Customer customer = new Customer();

            customer.setID(persistedUser.getID());
            customer.setFirstName(user.getFirstName());
            customer.setLastName(user.getLastName());
            customer.setPhoneNumber(user.getPhoneNumber());
            customer.setEmail(user.getEmail());
            customer.setPassword(hashedPassword);

            customer.setFeedbacks(feedbackService.getAllUserFeedbacks(persistedUser.getID()));
            customer.setReservations(List.of(reservationService.getReservationByCustomerID(persistedUser.getID())));

            Customer storedUser = customerRepository.save(customer);
            log.info("Customer with email {} successfully updated", storedUser.getEmail());
            return UserMapper.INSTANCE.customerToUser(customer);
        } else {
            Administrator admin = new Administrator();

            admin.setID(persistedUser.getID());
            admin.setFirstName(user.getFirstName());
            admin.setLastName(user.getLastName());
            admin.setPhoneNumber(user.getPhoneNumber());
            admin.setEmail(user.getEmail());
            admin.setPassword(hashedPassword);

            Administrator storedUser = administratorRepository.save(admin);
            log.info("Administrator with email {} successfully updated", storedUser.getEmail());
            return UserMapper.INSTANCE.administratorToUser(storedUser);
        }
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        log.info("Deleting the User with email {}", email);
        repository.deleteByEmail(email);
        log.info("User with email {} successfully deleted", email);
    }

    private String getDiscriminatorOfUser(User user) {
        if (user instanceof Customer)
            return "customer";
        else
            return "admin";
    }
}
