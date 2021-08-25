/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.model.Admin;
import com.project4.hobookstore.controller.AdminJpaController;
import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author DuongPH
 */
public class AdminService implements Serializable{

//    public static void main(String[] args) {
//        AdminService dao = new AdminService();
//        List<Admin> list = dao.findAllAdmin();
//        for (Admin admin : list) {
//            System.out.println("Admin: " + admin);
//        }
//    }
    
    public AdminService() {
        
    }
    
    public List<Admin> findAllAdmin(){
        AdminJpaController adminJPA = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return adminJPA.findAdminEntities();
    }
    
    public Admin findAdmin(String username){
        AdminJpaController adminJPA = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return adminJPA.findAdmin(username);
    }
    
    public List<Admin> findAdminByName(String name){
        AdminJpaController jpaController = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.findAdminByName(name);
    }
    
    public NotifyMessage createAdmin(Admin adm){
        AdminJpaController adminJPA = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if(findAdmin(adm.getUsername()) != null){
                msg.setMsg(Constant.REGISTER_FAIL);
                msg.setCode(Constant.REGISTER_CODE_ACC_EXIST);
            } else if(findAdmin(adm.getUsername()) == null) {
                adminJPA.create(adm);
                msg.setMsg(Constant.REGISTER_SUCCESS);
                msg.setCode(Constant.REGISTER_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public NotifyMessage updateAdmin(Admin adm){
        AdminJpaController adminJPA = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if(findAdmin(adm.getUsername()) == null){
                msg.setMsg(Constant.UPDATE_FAIL);
                msg.setCode(Constant.UPDATE_CODE_ACC_NOTEXIST);
            } else if(findAdmin(adm.getUsername()) != null) {
                adminJPA.edit(adm);
                msg.setMsg(Constant.UPDATE_SUCCESS);
                msg.setCode(Constant.UPDATE_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public NotifyMessage deleteAdmin(Admin adm){
        AdminJpaController adminJPA = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        NotifyMessage msg = new NotifyMessage();
        try {
            if(findAdmin(adm.getUsername()) == null){
                msg.setMsg(Constant.DELETE_FAIL);
                msg.setCode(Constant.DELETE_CODE_ACC_NOTEXIST);
            } else if(findAdmin(adm.getUsername()) != null) {
                adminJPA.destroy(adm.getUsername());
                msg.setMsg(Constant.DELETE_SUCCESS);
                msg.setCode(Constant.DELETE_CODE_SUSCCESS);
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}

