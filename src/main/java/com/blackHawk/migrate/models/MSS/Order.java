package com.blackHawk.migrate.models.MSS;

import com.blackHawk.migrate.models.MSS.Orderline;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    private int id;

    private Date orderdate;

    private float total;
    
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Orderline> orderlines = new ArrayList<>();


    public Order() {
    }

    public Order(int id, Date orderdate, float total, Customer customer) {
        this.id = id;
        this.orderdate = orderdate;
        this.total = total;
        this.customer = customer;
        this.orderlines = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public void addOrderline(Orderline o )
    {
        orderlines.add(o);
    }

}
