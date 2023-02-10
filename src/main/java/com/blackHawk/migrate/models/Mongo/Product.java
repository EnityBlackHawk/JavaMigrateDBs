package com.blackHawk.migrate.models.Mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class Product {

    @Id
    private String id;

    private String descrip;
    private float price;

    public Product() {
    }

    public Product(String id, String descrip, float price) {
        this.id = id;
        this.descrip = descrip;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
