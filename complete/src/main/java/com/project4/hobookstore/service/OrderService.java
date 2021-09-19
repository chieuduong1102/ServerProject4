/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.controller.Order1JpaController;
import com.project4.hobookstore.controller.OrderdetailJpaController;
import com.project4.hobookstore.model.Order1;
import com.project4.hobookstore.model.Orderdetail;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author T480s
 */
public class OrderService implements Serializable {

    public OrderService() {
    }

    public Order1 createOrder(Order1 order) {
        Order1JpaController order1JpaController = new Order1JpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        order1JpaController.createNewOrder(order);
        return order;
    }
    
    public Order1 getOrderByOrderId(Integer id) {
        Order1JpaController order1JpaController = new Order1JpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return order1JpaController.findOrder1(id);
    }

    public Order1 findOrderById(Integer id) {
        Order1JpaController order1JpaController = new Order1JpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return order1JpaController.findOrder1(id);
    }

    public void createOrderDetail(Orderdetail orderDetail) {
        OrderdetailJpaController orderdetailJpaController = new OrderdetailJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        orderdetailJpaController.createNewOrderDetail(orderDetail);
    }

    public List<Orderdetail> getOrderDetailByOrderId(Integer id) {
        Order1JpaController order1JpaController = new Order1JpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        OrderdetailJpaController orderdetailJpaController = new OrderdetailJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Order1 order = order1JpaController.findOrder1(id);
        return orderdetailJpaController.getOrderDetailByOrderId(order);
    }
    
    public List<Order1> getOrderList(){
        Order1JpaController order1JpaController = new Order1JpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return order1JpaController.findOrder1Entities();
    }
    
    public int updateOrderStatus(Integer oid,Integer status){
       Order1JpaController order1JpaController = new Order1JpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
       return order1JpaController.updateOrderStatus(oid, status);
    }
}

