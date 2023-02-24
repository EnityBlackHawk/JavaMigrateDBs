package com.blackHawk.migrate.BaseClasses;

import com.blackHawk.migrate.Annotations.AddAnnotation;

import javax.persistence.Id;

public interface TesteInterface {

    @AddAnnotation(Id.class)
    void setId(int value);
    void setName(String value);

}
