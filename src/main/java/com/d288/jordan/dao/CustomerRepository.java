package com.d288.jordan.dao;

import com.d288.jordan.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // idempotency check for BootstrapData
    boolean existsByPhone(String phone);
}
