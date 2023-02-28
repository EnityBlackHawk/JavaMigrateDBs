package com.blackHawk.migrate.BaseClasses;

import com.blackHawk.migrate.Annotations.AddAnnotation;

import javax.persistence.Id;
import java.util.Date;

public interface CustomerInterface {

    @AddAnnotation(Id.class)
    public void setId(String id);

    public void setName(String name);

    public void setBirth(Date birth);

    public void setBudget(float budget);
}
