package com.jordanportfolio.travelbooking.dao;

import com.jordanportfolio.travelbooking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // idempotency check for BootstrapData
    boolean existsByPhone(String phone);
}
