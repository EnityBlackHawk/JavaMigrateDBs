package com.blackHawk.migrate.repositories.MSS;

import com.blackHawk.migrate.models.MSS.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
