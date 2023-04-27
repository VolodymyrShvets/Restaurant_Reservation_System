package com.restaurant.system.service.api;

import com.restaurant.system.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant addNewRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(long id);

    List<Restaurant> getAllRestaurants();

    Restaurant updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(long id);
}
