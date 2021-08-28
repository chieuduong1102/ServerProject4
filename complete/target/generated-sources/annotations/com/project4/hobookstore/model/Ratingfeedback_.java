package com.project4.hobookstore.model;

import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-28T16:25:18")
@StaticMetamodel(Ratingfeedback.class)
public class Ratingfeedback_ { 

    public static volatile SingularAttribute<Ratingfeedback, String> feedback;
    public static volatile SingularAttribute<Ratingfeedback, Integer> scoreRate;
    public static volatile SingularAttribute<Ratingfeedback, Integer> id;
    public static volatile SingularAttribute<Ratingfeedback, Book> bid;
    public static volatile SingularAttribute<Ratingfeedback, User> username;

}