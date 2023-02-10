package com.blackHawk.migrate.Controllers.Mongo;

import com.blackHawk.migrate.models.Mongo.Order;
import com.blackHawk.migrate.repositories.Mongo.MgOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mongo/order")
public class MgOrderController {

    @Autowired
    private MgOrderRepository repository;

    @GetMapping
    public ResponseEntity<List<Order>> getAll()
    {
        return ResponseEntity.ok(repository.findAll());
    }

}
