package com.restaurant.system.controller;

import com.restaurant.system.model.Restaurant;
import com.restaurant.system.service.api.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private RestaurantService restaurantService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Restaurant addNewRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addNewRestaurant(restaurant);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable long id) {
        return restaurantService.getRestaurant(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(restaurant);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable long id) {
        restaurantService.deleteRestaurant(id);
    }
}
