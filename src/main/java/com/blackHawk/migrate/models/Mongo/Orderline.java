package com.blackHawk.migrate.models.Mongo;

import com.blackHawk.migrate.BaseClasses.AutoOrderClass;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Id;
import java.util.Date;


public class Orderline {

    @Id
    private String id;

    private Date orderlinedate;

    private float quantity;

    private float price;

    @DocumentReference
    private AutoOrderClass order;

    @DocumentReference
    private Product product;

    public Orderline() {
    }

    public Orderline(String id, Date orderlinedate, float quantity, float price, AutoOrderClass order, Product product) {
        this.id = id;
        this.orderlinedate = orderlinedate;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public AutoOrderClass getOrder() {
        return order;
    }

    public void setOrder(AutoOrderClass order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
