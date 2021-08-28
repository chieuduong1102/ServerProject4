/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.controller.UserJpaController;
import com.project4.hobookstore.model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author DuongPH
 */
public class UserService {
    public UserService() {
        
    }
    
//    public static void main(String[] args) {
//        UserService us = new UserService();
//        User u = us.findUser("Username0");
//        System.out.println("UN :" + u.getUsername());
//    }
    
    public List<User> findAllUser(){
        UserJpaController userJPA = new UserJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return userJPA.findUserEntities();
    }
    
    public User findUser(String username){
        UserJpaController userJPA = new UserJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return userJPA.findUser(username);
    }
    
    
    public NotifyMessage createUser(User user){
        UserJpaController userJPA = new UserJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if(findUser(user.getUsername()) != null){
                msg.setMsg(Constant.REGISTER_FAIL);
                msg.setCode(Constant.REGISTER_CODE_ACC_EXIST);
            } else if(findUser(user.getUsername()) == null) {
                userJPA.create(user);
                msg.setMsg(Constant.REGISTER_SUCCESS);
                msg.setCode(Constant.REGISTER_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public NotifyMessage updateUser(User user){
        UserJpaController userJPA = new UserJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if(findUser(user.getUsername()) == null){
                msg.setMsg(Constant.UPDATE_FAIL);
                msg.setCode(Constant.UPDATE_CODE_ACC_NOTEXIST);
            } else if(findUser(user.getUsername()) != null) {
                userJPA.edit(user);
                msg.setMsg(Constant.UPDATE_SUCCESS);
                msg.setCode(Constant.UPDATE_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public NotifyMessage deleteUser(User user){
        UserJpaController userJPA = new UserJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if(findUser(user.getUsername()) == null){
                msg.setMsg(Constant.DELETE_FAIL);
                msg.setCode(Constant.DELETE_CODE_ACC_NOTEXIST);
            } else if(findUser(user.getUsername()) != null) {
                userJPA.destroy(user.getUsername());
                msg.setMsg(Constant.DELETE_SUCCESS);
                msg.setCode(Constant.DELETE_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
