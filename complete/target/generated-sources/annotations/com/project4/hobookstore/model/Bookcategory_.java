package com.project4.hobookstore.model;

import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-10T18:29:13")
@StaticMetamodel(Bookcategory.class)
public class Bookcategory_ { 

    public static volatile SingularAttribute<Bookcategory, Integer> id;
    public static volatile SingularAttribute<Bookcategory, Book> bid;
    public static volatile SingularAttribute<Bookcategory, Category> cid;

}