package com.example.restservice.model;

import com.example.restservice.model.Order1;
import com.example.restservice.model.Ratingfeedback;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-22T20:08:19")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, String> phonenumber;
    public static volatile ListAttribute<User, Order1> order1List;
    public static volatile SingularAttribute<User, String> fullname;
    public static volatile ListAttribute<User, Ratingfeedback> ratingfeedbackList;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}