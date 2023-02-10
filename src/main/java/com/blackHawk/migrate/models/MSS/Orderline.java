package com.blackHawk.migrate.models.MSS;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_orderlines")
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date orderlinedate;

    private float quantity;
    private float price;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    public Orderline() {
    }

    public Orderline(int id, Date ordelinedate, float quantity, float price, Order order, Product product) {
        this.id = id;
        this.orderlinedate = ordelinedate;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderlinedate() {
        return orderlinedate;
    }

    public void setOrderlinedate(Date orderlinedate) {
        this.orderlinedate = orderlinedate;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
