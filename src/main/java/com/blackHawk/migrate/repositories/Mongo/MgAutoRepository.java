package com.blackHawk.migrate.repositories.Mongo;


import com.blackHawk.migrate.BaseClasses.AutoClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MgAutoRepository extends MongoRepository<AutoClass, String> {
}
