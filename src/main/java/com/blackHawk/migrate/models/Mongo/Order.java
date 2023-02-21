package com.blackHawk.migrate.models.Mongo;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Reference;

@Document
public class Order implements com.blackHawk.migrate.BaseClasses.OrderInterface {

    @Id
    private String id;
    private Date orderdate;

    private float total;
    
    @DocumentReference
    private Customer customer;

   @DocumentReference
    private List<Orderline> orderlines;

    public Order() {
    }

    public Order(String id, Date orderdate, float total, Customer customer, List<Orderline> orderlines) {
        this.id = id;
        this.orderdate = orderdate;
        this.total = total;
        this.customer = customer;
        this.orderlines = orderlines;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Date getOrderdate() {
        return orderdate;
    }

    @Override
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public float getTotal() {
        return total;
    }

    @Override
    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

//    public void setCustomer(com.blackHawk.migrate.models.MSS.Customer customer) {
//        this.customer = new Customer(String.format("%d", customer.getId()), customer.getName(), customer.getBirth(), customer.getBudget());
//    }

    @Override
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    @Override
    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    @Override
    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    @Override
    public void addOrderlines(Orderline o)
    {
        orderlines.add(o);
    }

}
