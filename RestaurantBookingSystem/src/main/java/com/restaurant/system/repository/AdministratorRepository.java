package com.restaurant.system.repository;

import com.restaurant.system.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    boolean existsByEmail(String email);
}
