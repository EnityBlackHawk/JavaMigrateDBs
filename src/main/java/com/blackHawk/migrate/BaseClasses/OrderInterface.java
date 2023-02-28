package com.blackHawk.migrate.BaseClasses;

import com.blackHawk.migrate.Annotations.AddAnnotation;
import com.blackHawk.migrate.Annotations.ConditionalAnnotation;
import com.blackHawk.migrate.models.Mongo.Customer;
import com.blackHawk.migrate.models.Mongo.Orderline;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public interface OrderInterface {

    @AddAnnotation(Id.class)
    void setId(String id);

    void setOrderdate(Date orderdate);

    void setTotal(float total);

    @AddAnnotation(DocumentReference.class)
    void setCustomer(Customer customer);

    @ConditionalAnnotation(value = DocumentReference.class, tag = "refOrderline", def = false)
    void setOrderlines(List<Orderline> orderlines);
}
