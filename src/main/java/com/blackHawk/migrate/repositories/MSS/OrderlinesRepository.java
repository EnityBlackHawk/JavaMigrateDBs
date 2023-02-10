package com.blackHawk.migrate.repositories.MSS;

import com.blackHawk.migrate.models.MSS.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlinesRepository extends JpaRepository<Orderline, Integer> {
}
