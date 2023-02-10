package com.blackHawk.migrate.repositories.Mongo;

import com.blackHawk.migrate.models.Mongo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MgOrderRepository extends MongoRepository<Order, String> {
}
