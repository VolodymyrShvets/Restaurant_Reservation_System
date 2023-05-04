package com.restaurant.system.service.impl;

import com.restaurant.system.model.Administrator;
import com.restaurant.system.model.Restaurant;
import com.restaurant.system.model.RestaurantTable;
import com.restaurant.system.model.exception.EntityAlreadyExistsException;
import com.restaurant.system.model.exception.EntityNotFoundException;
import com.restaurant.system.repository.AdministratorRepository;
import com.restaurant.system.repository.RestaurantRepository;
import com.restaurant.system.repository.TableRepository;
import com.restaurant.system.service.api.TableService;
import com.restaurant.system.service.mapper.TableMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {
    private TableRepository repository;
    @Lazy
    private AdministratorRepository administratorRepository;
    @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantTable addNewTable(RestaurantTable table) {
        log.info("Creating new Table with id {}", table.getID());

        if (repository.existsById(table.getID()))
            throw new EntityAlreadyExistsException(RestaurantTable.class, "id " + table.getID());

        Optional<Administrator> admin = administratorRepository.findById(table.getAdministrator().getID());
        if (admin.isEmpty())
            throw new IllegalArgumentException();

        Optional<Restaurant> restaurant = restaurantRepository.findById(table.getRestaurant().getID());
        if (restaurant.isEmpty())
            throw new IllegalArgumentException();

        table.setAdministrator(admin.get());
        table.setRestaurant(restaurant.get());

        RestaurantTable newTable = repository.save(table);

        log.info("Table with id {} successfully created", table.getID());
        return newTable;
    }

    @Override
    public RestaurantTable getTable(long id) {
        log.info("Getting Table with id {}", id);

        Optional<RestaurantTable> table = repository.findById(id);
        if (table.isEmpty())
            throw new IllegalArgumentException();

        return table.get();
    }

    @Override
    public List<RestaurantTable> getAllTablesForRestaurant(long restaurantId) {
        log.info("Getting all Tables in restaurant with id {}", restaurantId);
        return repository.findAllByRestaurantID(restaurantId);
    }

    @Override
    public RestaurantTable updateTable(RestaurantTable table) {
        log.info("Updating Table with id {}", table.getID());

        Optional<RestaurantTable> optionalTable = repository.findById(table.getID());
        if (optionalTable.isEmpty())
            throw new EntityNotFoundException(RestaurantTable.class, "id " + table.getID());

        RestaurantTable newTable = TableMapper.INSTANCE.populateTableWithPresentTableFields(optionalTable.get(), table);
        RestaurantTable storedTable = repository.save(newTable);

        log.info("Table with id {} successfully updated", storedTable.getID());
        return storedTable;
    }

    @Override
    public void deleteTable(long id) {
        log.info("Deleting the Table with id {}", id);
        repository.deleteById(id);
    }
}
