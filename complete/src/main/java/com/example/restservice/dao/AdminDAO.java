/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.dao;

import com.example.restservice.model.Admin;
import com.example.restservice.model.AdminJpaController;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author DuongPH
 */
public class AdminDAO implements Serializable{

    public AdminDAO() {
        
    }
    
    public List<Admin> findAllAdmin(){
        AdminJpaController adminJPA = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return adminJPA.findAdminEntities();
    }
    
    public Admin findAdmin(String username){
        AdminJpaController adminJPA = new AdminJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return adminJPA.findAdmin(username);
    }
}
