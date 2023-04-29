package com.restaurant.system.controller;

import com.restaurant.system.model.RestaurantTable;
import com.restaurant.system.service.api.TableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class TableController {
    private TableService tableService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RestaurantTable addNewTable(@RequestBody RestaurantTable table) {
        return tableService.addNewTable(table);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public RestaurantTable getTable(@PathVariable long id) {
        return tableService.getTable(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{restId}")
    public List<RestaurantTable> getAllTablesForRestaurant(@PathVariable long restId) {
        return tableService.getAllTablesForRestaurant(restId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public RestaurantTable updateTable(@RequestBody RestaurantTable table) {
        return tableService.updateTable(table);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable long id) {
        tableService.deleteTable(id);
    }
}
