package com.restaurant.system.service.impl;

import com.restaurant.system.model.Table;
import com.restaurant.system.repository.TableRepository;
import com.restaurant.system.service.api.TableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {
    private TableRepository repository;

    @Override
    public Table addNewTable(Table table) {
        log.info("Creating new Table with id {}", table.getID());

        if (repository.existsById(table.getID()))
            throw new IllegalArgumentException(); // TODO

        Table newTable = repository.save(table);

        log.info("Table with id {} successfully created", table.getID());
        return newTable;
    }

    @Override
    public Table getTable(long id) {
        log.info("Getting Table with id {}", id);

        Optional<Table> table = repository.findById(id);
        if (table.isEmpty())
            throw new IllegalArgumentException();

        return table.get();
    }

    @Override
    public List<Table> getAllTablesForRestaurant(long restaurantId) {
        log.info("Getting all Tables in restaurant with id {}", restaurantId);
        return repository.findAllByRestaurantID(restaurantId);
    }

    @Override
    public Table updateTable(Table table) {
        log.info("Updating Table with id {}", table.getID());

        Optional<Table> optionalTable = repository.findById(table.getID());
        if (optionalTable.isEmpty())
            throw new IllegalArgumentException(); // TODO

        //persistedUser = populateWithPresentFields(); // TODO create method
        Table storedTable = repository.save(optionalTable.get());

        log.info("Table with id {} successfully updated", storedTable.getID());
        return storedTable;
    }

    @Override
    public void deleteTable(long id) {
        log.info("Deleting the Table with id {}", id);
        repository.deleteById(id);
    }
}
