package com.restaurant.system.controller;

import com.restaurant.system.model.Table;
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
    public Table addNewTable(@RequestBody Table table) {
        return tableService.addNewTable(table);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Table getTable(@PathVariable long id) {
        return tableService.getTable(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{restId}")
    public List<Table> getAllTablesForRestaurant(@PathVariable long restId) {
        return tableService.getAllTablesForRestaurant(restId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Table updateTable(@RequestBody Table table) {
        return tableService.updateTable(table);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable long id) {
        tableService.deleteTable(id);
    }
}
