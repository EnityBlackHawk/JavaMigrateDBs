package com.blackHawk.migrate.Controllers.MSS;

import com.blackHawk.migrate.models.MSS.Orderline;
import com.blackHawk.migrate.repositories.MSS.OrderlinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orderlines")
public class OrderlineController {
    @Autowired
    private OrderlinesRepository repository;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Orderline>> GetAll()
    {
        return ResponseEntity.ok(repository.findAll());
    }
}
