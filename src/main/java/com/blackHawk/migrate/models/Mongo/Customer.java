package com.blackHawk.migrate.models.Mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document
public class Customer {
    @Id
    private  String id;
    private String name;
    private Date birth;
    private float budget;

    public Customer() {
    }

    public Customer(String id, String name, Date birth, float budget) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.budget = budget;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
}
