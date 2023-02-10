package com.blackHawk.migrate;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
public class IndexTable{

    private HashMap<Class, HashMap<Integer, String>> typeTable;

    public IndexTable() {
        typeTable = new HashMap<>();
    }


    public String Register(Class typeMSS, int indexMSS)
    {
        ObjectId oid = new ObjectId();
        String s = oid.toString();

        var hash = typeTable.get(typeMSS);

        if(hash == null)
        {

            typeTable.put(typeMSS, new HashMap<>());
            hash = typeTable.get(typeMSS);
        }

        hash.put(indexMSS, oid.toString());

        return s;
    }

    public void Register(Class typeMSS, int indexMSS, String indexMongo)
    {
        var hash = typeTable.get(typeMSS);
        if(hash == null)
        {
            typeTable.put(typeMSS, new HashMap<>());
            hash = typeTable.get(typeMSS);
        }

        hash.put(indexMSS, indexMongo);

    }


    public String GetMongoIndex(Class typeMSS, int indexMss)
    {
        return typeTable.get(typeMSS).get(indexMss);
    }

}
