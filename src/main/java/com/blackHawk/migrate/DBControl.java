package com.blackHawk.migrate;

import com.blackHawk.migrate.BaseClasses.AutoClass;
import com.blackHawk.migrate.models.MSS.Customer;
import com.blackHawk.migrate.models.MSS.Order;
import com.blackHawk.migrate.models.MSS.Orderline;
import com.blackHawk.migrate.models.Mongo.Product;
import com.blackHawk.migrate.repositories.MSS.*;
import com.blackHawk.migrate.repositories.Mongo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBControl {

    @Autowired
    private MgProductRespository mongoProductRepository;

    @Autowired
    private MgOrderRepository mongoOrderRepository;

    @Autowired
    private MgCustomerRepository mongoCustomerRepository;

    @Autowired
    private MgAutoRepository mgTesteRepository;

    @Autowired
    private MgOrderlineRepository mongoOrderlineRepository;

    @Autowired
    private OrderRepository mssOrderRepository;

    @Autowired
    private CustomerRepository mssCustomerRepository;

    @Autowired
    private OrderlinesRepository mssOrderlineRepository;

    @Autowired
    private ProductRepository mssProductRepository;

    public void SaveMongo(Product p)
    {
        mongoProductRepository.save(p);
    }

    public void SaveMongo(com.blackHawk.migrate.models.Mongo.Order o)
    {
        mongoOrderRepository.save(o);
    }

    public void SaveMongo(com.blackHawk.migrate.models.Mongo.Customer c)
    {
        mongoCustomerRepository.save(c);
    }

    public void SaveMongo(AutoClass c) { mgTesteRepository.save(c);}

    public void SaveMongo(com.blackHawk.migrate.models.Mongo.Orderline orderline)
    {
        mongoOrderlineRepository.save(orderline);
    }

    public com.blackHawk.migrate.models.Mongo.Customer GetCustomerMongo(String id)
    {
        return mongoCustomerRepository.findById(id).get();
    }

    public List<com.blackHawk.migrate.models.Mongo.Customer> GetCustomerMongo()
    {
        return mongoCustomerRepository.findAll();
    }

    public List<com.blackHawk.migrate.models.Mongo.Orderline> GetOrderlines()
    {
        return mongoOrderlineRepository.findAll();
    }

    public List<com.blackHawk.migrate.models.Mongo.Order> GetOrderMongo()
    {
        return mongoOrderRepository.findAll();
    }

    public List<Orderline> GetOrderlineMSS()
    {
        return mssOrderlineRepository.findAll();
    }

    public List<com.blackHawk.migrate.models.MSS.Product> GetProductMSS() {return mssProductRepository.findAll();}

    public List<Order> GetOrderMSS()
    {
        return mssOrderRepository.findAll();
    }

    public List<Customer> GetCustomerMSS()
    {
        return mssCustomerRepository.findAll();
    }

    public Object GetFromMongo(Class T, String index)
    {
        if(T.equals(com.blackHawk.migrate.models.Mongo.Customer.class))
        {
           var ret =  mongoCustomerRepository.findById(index);
           return ret.isPresent() ? ret.get() : null;
        }
        if(T.equals(Product.class))
        {
            var ret =  mongoProductRepository.findById(index);
            return ret.isPresent() ? ret.get() : null;
        }
        if(T.equals(com.blackHawk.migrate.models.Mongo.Order.class))
        {
            var ret =  mongoOrderRepository.findById(index);
            return ret.isPresent() ? ret.get() : null;
        }
        if(T.equals(com.blackHawk.migrate.models.Mongo.Orderline.class))
        {
            var ret =  mongoOrderlineRepository.findById(index);
            return ret.isPresent() ? ret.get() : null;
        }
        return null;
    }
    
    public void SaveMSS (Orderline obj)
    {
        mssOrderlineRepository.save(obj);
    }

}
