package com.blackHawk.migrate.BaseClasses;

import com.blackHawk.migrate.models.Mongo.Customer;
import com.blackHawk.migrate.models.Mongo.Orderline;

import java.util.Date;
import java.util.List;

public interface OrderInterface {
    String getId();

    void setId(String id);

    Date getOrderdate();

    void setOrderdate(Date orderdate);

    float getTotal();

    void setTotal(float total);

    Customer getCustomer();

    void setCustomer(Customer customer);

    List<Orderline> getOrderlines();

    void setOrderlines(List<Orderline> orderlines);

    void addOrderlines(Orderline o);
}
