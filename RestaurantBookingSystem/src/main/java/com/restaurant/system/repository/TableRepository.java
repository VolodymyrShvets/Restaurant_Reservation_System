package com.restaurant.system.repository;

import com.restaurant.system.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
    List<Table> findAllByRestaurantID(long id);
}
