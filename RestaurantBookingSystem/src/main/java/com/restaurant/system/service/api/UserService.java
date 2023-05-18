package com.restaurant.system.service.api;

import com.restaurant.system.model.Administrator;
import com.restaurant.system.model.Customer;
import com.restaurant.system.model.User;

import java.util.List;

public interface UserService {
    Customer registerNewCustomer(Customer customer);

    Administrator registerNewAdministrator(Administrator admin);

    User getUser(String email);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(String email);

    Customer getCustomer(String customerEmail);
}
