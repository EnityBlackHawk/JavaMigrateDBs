package com.blackHawk.migrate.repositories.Mongo;

import com.blackHawk.migrate.models.Mongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MgProductRespository extends MongoRepository<Product, String> {
}
