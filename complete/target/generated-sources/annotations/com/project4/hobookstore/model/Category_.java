package com.project4.hobookstore.model;

import com.project4.hobookstore.model.Bookcategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-28T16:25:18")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile ListAttribute<Category, Bookcategory> bookcategoryList;
    public static volatile SingularAttribute<Category, String> categoryName;
    public static volatile SingularAttribute<Category, Integer> cid;

}