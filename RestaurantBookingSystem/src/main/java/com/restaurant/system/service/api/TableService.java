package com.restaurant.system.service.api;

import com.restaurant.system.model.RestaurantTable;

import java.util.List;

public interface TableService {
    RestaurantTable addNewTable(RestaurantTable table);

    RestaurantTable getTable(long id);

    List<RestaurantTable> getAllTablesForRestaurant(long restaurantId);

    RestaurantTable updateTable(RestaurantTable table);

    void deleteTable(long id);
}
