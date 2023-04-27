package com.restaurant.system.service.api;

import com.restaurant.system.model.Table;

import java.util.List;

public interface TableService {
    Table addNewTable(Table table);

    Table getTable(long id);

    List<Table> getAllTablesForRestaurant(long restaurantId);

    Table updateTable(Table table);

    void deleteTable(long id);
}
