package com.example.restservice.model;

import com.project4.restservice.model.Image;
import com.project4.restservice.model.Book;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-24T17:16:51")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Integer> iid;
    public static volatile SingularAttribute<Image, String> nameFile;
    public static volatile SingularAttribute<Image, Book> bid;

}