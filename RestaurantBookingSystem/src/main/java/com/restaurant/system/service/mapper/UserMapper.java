package com.restaurant.system.service.mapper;

import com.restaurant.system.model.Administrator;
import com.restaurant.system.model.Customer;
import com.restaurant.system.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User customerToUser(Customer customer);

    User administratorToUser(Administrator administrator);
}
