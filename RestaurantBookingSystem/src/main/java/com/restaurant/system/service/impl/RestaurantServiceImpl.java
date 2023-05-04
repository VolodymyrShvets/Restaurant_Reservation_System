package com.restaurant.system.service.impl;

import com.restaurant.system.model.Restaurant;
import com.restaurant.system.model.exception.EntityAlreadyExistsException;
import com.restaurant.system.model.exception.EntityNotFoundException;
import com.restaurant.system.repository.RestaurantRepository;
import com.restaurant.system.service.api.RestaurantService;
import com.restaurant.system.service.mapper.RestaurantMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository repository;

    @Override
    public Restaurant addNewRestaurant(Restaurant restaurant) {
        log.info("Creating new Restaurant with id {}", restaurant.getID());

        if (repository.existsById(restaurant.getID()))
            throw new EntityAlreadyExistsException(Restaurant.class, "id " + restaurant.getID());

        Restaurant newRestaurant = repository.save(restaurant);

        log.info("Restaurant with id {} successfully created", restaurant.getID());
        return newRestaurant;
    }

    @Override
    public Restaurant getRestaurant(long id) {
        log.info("Getting Restaurant with id {}", id);

        Optional<Restaurant> restaurant = repository.findById(id);
        if (restaurant.isEmpty())
            throw new EntityNotFoundException(Restaurant.class, "id " + id);

        return restaurant.get();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        log.info("Getting all Restaurants");
        return repository.findAll();
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        log.info("Updating Restaurant with id {}", restaurant.getID());

        Optional<Restaurant> optionalRestaurant = repository.findById(restaurant.getID());
        if (optionalRestaurant.isEmpty())
            throw new EntityNotFoundException(Restaurant.class, "id " + restaurant.getID());

        Restaurant newRestaurant = RestaurantMapper.INSTANCE.populateRestaurantWithPresentRestaurantFields(optionalRestaurant.get(), restaurant);
        Restaurant storedRestaurant = repository.save(newRestaurant);

        log.info("Restaurant with id {} successfully updated", storedRestaurant.getID());
        return storedRestaurant;
    }

    @Override
    public void deleteRestaurant(long id) {
        log.info("Deleting the Restaurant with id {}", id);
        repository.deleteById(id);
    }
}
