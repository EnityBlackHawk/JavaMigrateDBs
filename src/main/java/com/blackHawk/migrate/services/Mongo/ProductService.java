package com.blackHawk.migrate.services.Mongo;

import com.blackHawk.migrate.models.Mongo.Product;
import com.blackHawk.migrate.repositories.Mongo.MgProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private MgProductRespository productRespository;

    public void Save(Product p)
    {
        productRespository.save(p);
    }

}
