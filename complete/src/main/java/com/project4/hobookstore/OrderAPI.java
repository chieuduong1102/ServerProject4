/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.OrderDTO;
import com.project4.hobookstore.dto.OrderdetailDTO;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Order1;
import com.project4.hobookstore.model.Orderdetail;
import com.project4.hobookstore.model.User;
import com.project4.hobookstore.service.BookService;
import com.project4.hobookstore.service.OrderService;
import com.project4.hobookstore.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author T480s
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderAPI {
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public Integer createOrder(@RequestBody OrderDTO order) {
        OrderService orderService = new OrderService();
        UserService userService = new UserService();
        try {
            User user = userService.findUser(order.getUserName());
            Order1 newOrder = new Order1(order.getTimeOrder(),
                    user,
                    order.getDeliveryAddress(),
                    order.getTotalPrice(),
                    order.getNote(),
                    order.getStatus());
            return orderService.createOrder(newOrder).getOid();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(path = "/orderdetail", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage createOrderDetail(@RequestBody OrderdetailDTO orderdetail) {
        OrderService orderService = new OrderService();
        BookService bookService = new BookService();
        NotifyMessage msg = new NotifyMessage();
        try {
            Book book = bookService.findBookByBId(orderdetail.getBid());
            Order1 order = orderService.findOrderById(orderdetail.getOid());
            Orderdetail newOrderDetail = new Orderdetail(orderdetail.getAmount(),
                    order,
                    book);
            orderService.createOrderDetail(newOrderDetail);
            msg.setCode(Constant.CREATE_SUCCESS);
            msg.setMsg("Create OrderDetail Success!");
        } catch (Exception e) {
            msg.setCode(Constant.CREATE_FAIL);
            msg.setMsg(e.toString());
        }
        return msg;
    }

    @GetMapping(path = "/getOrderDetail")
    public List<OrderdetailDTO> getOrdersDetailByOrderId(@RequestParam(name = "oid") Integer oid) {
        OrderService orderSer = new OrderService();
        return orderSer.getOrderDetailByOrderId(oid).stream().map(detail -> modelMapper.map(detail, OrderdetailDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/getOrder")
    public OrderDTO getOrdersByOrderId(@RequestParam(name = "oid") Integer oid) {
        OrderService orderSer = new OrderService();
        Order1 order =  orderSer.getOrderByOrderId(oid);
        return modelMapper.map(order, OrderDTO.class);
    }
    
    @GetMapping("/getOrderList")
    public List<OrderDTO> getOrderList() {
        OrderService orderSer = new OrderService();
        return orderSer.getOrderList().stream()
                .sorted((Order1 o1, Order1 o2) -> o2.getTimeOrder().compareTo(o1.getTimeOrder()))
                .map(book -> modelMapper.map(book, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/updateStatus")
    public NotifyMessage updateStatus(@RequestParam(name = "oid") Integer oid, @RequestParam(name = "status") Integer status) {
        OrderService orderSer = new OrderService();
        NotifyMessage message = new NotifyMessage();
        int result = 0;
        try {
            result = orderSer.updateOrderStatus(oid, status);
        } catch (Exception e) {
            message.setCode(Constant.UPDATE_CODE_FAIL);
            message.setMsg(e.toString());
        }
        if (result >0) {
            message.setCode(Constant.UPDATE_CODE_SUSCCESS);
            message.setMsg("Cập nhật trạng thái hóa đơn thành công");
        }else{
            message.setCode(Constant.UPDATE_CODE_FAIL);
            message.setMsg("Cập nhật trạng thái hóa đơn thất bại");
        }
        return message;
    }

}

