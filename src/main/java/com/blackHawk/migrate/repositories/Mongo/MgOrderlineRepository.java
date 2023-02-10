package com.blackHawk.migrate.repositories.Mongo;

import com.blackHawk.migrate.models.Mongo.Orderline;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MgOrderlineRepository extends MongoRepository<Orderline, String> {
}
