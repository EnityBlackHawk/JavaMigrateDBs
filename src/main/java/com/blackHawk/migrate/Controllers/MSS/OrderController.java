package com.blackHawk.migrate.Controllers.MSS;

import com.blackHawk.migrate.models.MSS.Order;
import com.blackHawk.migrate.repositories.MSS.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> GetAll()
    {
        return ResponseEntity.ok(repository.findAll());
    }
}
