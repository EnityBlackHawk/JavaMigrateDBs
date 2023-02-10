package com.blackHawk.migrate.repositories.MSS;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blackHawk.migrate.models.MSS.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
