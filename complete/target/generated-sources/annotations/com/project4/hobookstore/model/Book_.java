package com.project4.hobookstore.model;

import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Image;
import com.project4.hobookstore.model.Orderdetail;
import com.project4.hobookstore.model.Ratingfeedback;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-10T18:29:13")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Integer> yearPublish;
    public static volatile SingularAttribute<Book, String> author;
    public static volatile ListAttribute<Book, Orderdetail> orderdetailList;
    public static volatile SingularAttribute<Book, String> description;
    public static volatile ListAttribute<Book, Bookcategory> bookcategoryList;
    public static volatile SingularAttribute<Book, String> titleBook;
    public static volatile ListAttribute<Book, Ratingfeedback> ratingfeedbackList;
    public static volatile SingularAttribute<Book, String> dateSale;
    public static volatile SingularAttribute<Book, String> manufacture;
    public static volatile SingularAttribute<Book, String> publishingCompany;
    public static volatile SingularAttribute<Book, Double> price;
    public static volatile SingularAttribute<Book, Integer> bid;
    public static volatile ListAttribute<Book, Image> imageList;
    public static volatile SingularAttribute<Book, Integer> status;

}