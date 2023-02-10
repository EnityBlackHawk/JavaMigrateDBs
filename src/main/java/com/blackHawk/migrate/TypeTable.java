package com.blackHawk.migrate;

import org.springframework.stereotype.Component;

import java.util.HashMap;


public class TypeTable {

    private HashMap<Class, Class> typeTable;

    public TypeTable()
    {
        typeTable = new HashMap<>();
    }

    public void Register(Class origin, Class dest)
    {
        typeTable.put(origin, dest);
    }

    public Class Get(Class origin)
    {
       return typeTable.get(origin);
    }

}
