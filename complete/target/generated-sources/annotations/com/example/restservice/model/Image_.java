package com.example.restservice.model;

import com.example.restservice.model.Book;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-22T20:08:19")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Integer> iid;
    public static volatile SingularAttribute<Image, String> nameFile;
    public static volatile SingularAttribute<Image, Book> bid;

}