package com.project4.hobookstore.model;

import com.project4.hobookstore.model.Orderdetail;
import com.project4.hobookstore.model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-28T16:25:18")
@StaticMetamodel(Order1.class)
public class Order1_ { 

    public static volatile SingularAttribute<Order1, String> note;
    public static volatile SingularAttribute<Order1, String> deliveryAddress;
    public static volatile SingularAttribute<Order1, Double> totalPrice;
    public static volatile ListAttribute<Order1, Orderdetail> orderdetailList;
    public static volatile SingularAttribute<Order1, Integer> oid;
    public static volatile SingularAttribute<Order1, String> timeOrder;
    public static volatile SingularAttribute<Order1, Integer> status;
    public static volatile SingularAttribute<Order1, User> username;

}