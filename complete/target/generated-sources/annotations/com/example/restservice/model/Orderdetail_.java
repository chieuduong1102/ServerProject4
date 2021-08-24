package com.example.restservice.model;

import com.example.restservice.model.Book;
import com.example.restservice.model.Order1;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-24T17:16:51")
@StaticMetamodel(Orderdetail.class)
public class Orderdetail_ { 

    public static volatile SingularAttribute<Orderdetail, Integer> amount;
    public static volatile SingularAttribute<Orderdetail, Integer> id;
    public static volatile SingularAttribute<Orderdetail, Order1> oid;
    public static volatile SingularAttribute<Orderdetail, Book> bid;

}