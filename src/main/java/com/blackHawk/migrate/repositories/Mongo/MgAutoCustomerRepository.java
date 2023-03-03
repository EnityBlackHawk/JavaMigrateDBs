package com.blackHawk.migrate.repositories.Mongo;

import com.blackHawk.migrate.BaseClasses.AutoCustomerClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MgAutoCustomerRepository extends MongoRepository<AutoCustomerClass, String> {
}
