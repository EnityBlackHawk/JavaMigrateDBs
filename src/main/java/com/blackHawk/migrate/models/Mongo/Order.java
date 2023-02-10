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
public class Order {

    @Id
    private String id;
    private Date orderdate;

    private float total;

    @Reference
    private Customer customer;

   @DBRef
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

//    public void setCustomer(com.blackHawk.migrate.models.MSS.Customer customer) {
//        this.customer = new Customer(String.format("%d", customer.getId()), customer.getName(), customer.getBirth(), customer.getBudget());
//    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public void addOrderlines(Orderline o)
    {
        orderlines.add(o);
    }

}
