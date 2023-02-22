package com.blackHawk.migrate.BaseClasses;

import com.blackHawk.migrate.Annotations.AddAnnotation;

import javax.persistence.Id;

public interface TesteInterface {

    @AddAnnotation(value = {Id.class})
    public int getId();
    public String getName();
    public void setId(int id);

}
