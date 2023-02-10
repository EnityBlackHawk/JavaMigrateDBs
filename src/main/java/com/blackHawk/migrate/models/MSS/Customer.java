package com.blackHawk.migrate.models.MSS;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_customers")
public class Customer {

    @Id
    private int id;
    private String name;
    private Date birth;
    private float budget;

    public Customer() {
    }

    public Customer(int id, String name, Date birth, float budget) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
