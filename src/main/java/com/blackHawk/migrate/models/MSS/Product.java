package com.blackHawk.migrate.models.MSS;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    private int id;

    private String descrip;
    private float price;

    public Product() {
    }

    public Product(int id, String descrip, float price) {
        this.id = id;
        this.descrip = descrip;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
