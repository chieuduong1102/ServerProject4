/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;

import com.example.restservice.dao.UserDAO;
import com.example.restservice.encode.Encode;
import com.example.restservice.model.User;
import com.example.restservice.model.Constant;
import com.example.restservice.model.NotifyMessage;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Web Service
 *
 * @author DuongPH
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserAPI {
    private List<User> list = new ArrayList<>();
    
    @GetMapping("/getAllUser")
    public List<User> findAllUser(){
        UserDAO userDAO = new UserDAO();
        return userDAO.findAllUser();
    }
    
    @GetMapping(path="/userInfo")
    public User getUserInfo(@RequestParam(name = "user") String username){
        UserDAO userDAO = new UserDAO();
        User userRoot = new User();
        userRoot = userDAO.findUser(username);
        return userRoot;
    }
    
    @GetMapping(path="/searchUser")
    public List<User> getUserInfosByFullname(@RequestParam(name = "param") String value){
        UserDAO userDAO = new UserDAO();
        return userDAO.findUsersByValueSearch(value);
    }
    
    @PostMapping(path="checkUser", consumes = MediaType.APPLICATION_JSON, 
        produces = MediaType.APPLICATION_JSON)
    public NotifyMessage checkUser(@RequestBody User user){
        UserDAO userDAO = new UserDAO();
        NotifyMessage notify = new NotifyMessage();
        User userRoot = new User();
        userRoot = userDAO.findUser(user.getUsername());
        if (userRoot == null) {
            //|| password != user.getPassword()
            notify.setMsg(Constant.LOGIN_FAIL);
            notify.setCode(Constant.LOGIN_CODE_FAIL);
        } else if (userRoot != null) {
            if (!userRoot.getPassword().equals(Encode.getSHAHash(user.getPassword())) || user.getPassword() == "" || user.getPassword() == null ) {
                notify.setMsg(Constant.LOGIN_FAIL);
                notify.setCode(Constant.LOGIN_CODE_FAIL);
            } else {
                //&& password == user.getPassword()
                notify.setMsg(Constant.LOGIN_SUCCESS);
                notify.setCode(Constant.LOGIN_CODE_SUSCCESS);
            }
        }
        return notify;
    }
    
    @PostMapping(path="create", consumes = MediaType.APPLICATION_JSON, 
        produces = MediaType.APPLICATION_JSON)
    public NotifyMessage createUserAccount(@RequestBody User user){
        UserDAO userDAO = new UserDAO();
        User newUser = new User();
        NotifyMessage msg = new NotifyMessage();
        try {
            newUser.setFullname(user.getFullname());
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setAddress(user.getAddress());
            newUser.setPhonenumber(user.getPhonenumber());
            newUser.setPassword(Encode.getSHAHash(user.getPassword()));
            msg = userDAO.createUser(newUser);
        } catch (Exception e) {
            
        }
        return msg;
    }
    
    @PostMapping(path="update", consumes = MediaType.APPLICATION_JSON, 
        produces = MediaType.APPLICATION_JSON)
    public NotifyMessage updateUserAccount(@RequestBody User user){
        UserDAO userDAO = new UserDAO();
        User updUser = new User();
        NotifyMessage msg = new NotifyMessage();
        try {
            updUser.setFullname(user.getFullname());
            updUser.setUsername(user.getUsername());
            updUser.setEmail(user.getEmail());
            updUser.setAddress(user.getAddress());
            updUser.setPhonenumber(user.getPhonenumber());
            updUser.setPassword(Encode.getSHAHash(user.getPassword()));
            msg = userDAO.updateUser(updUser);
        } catch (Exception e) {
            
        }
        return msg;
    }
    
    @PostMapping(path="delete", consumes = MediaType.APPLICATION_JSON, 
        produces = MediaType.APPLICATION_JSON)
    public NotifyMessage deleteUserAccount(@RequestBody User user){
        UserDAO userDAO = new UserDAO();
        User delUser = new User();
        NotifyMessage msg = new NotifyMessage();
        try {
            delUser.setFullname(user.getFullname());
            delUser.setUsername(user.getUsername());
            delUser.setEmail(user.getEmail());
            delUser.setAddress(user.getAddress());
            delUser.setPhonenumber(user.getPhonenumber());
            delUser.setPassword(Encode.getSHAHash(user.getPassword()));
            msg = userDAO.deleteUser(delUser);
        } catch (Exception e) {
            
        }
        return msg;
    }
}
