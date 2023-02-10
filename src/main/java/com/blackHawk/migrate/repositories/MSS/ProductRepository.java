package com.blackHawk.migrate.repositories.MSS;

import com.blackHawk.migrate.models.MSS.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
