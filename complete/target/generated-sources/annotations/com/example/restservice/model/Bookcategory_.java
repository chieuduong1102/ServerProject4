package com.example.restservice.model;

import com.project4.restservice.model.Bookcategory;
import com.project4.restservice.model.Book;
import com.project4.restservice.model.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-24T17:16:51")
@StaticMetamodel(Bookcategory.class)
public class Bookcategory_ { 

    public static volatile SingularAttribute<Bookcategory, Integer> id;
    public static volatile SingularAttribute<Bookcategory, Book> bid;
    public static volatile SingularAttribute<Bookcategory, Category> cid;

}