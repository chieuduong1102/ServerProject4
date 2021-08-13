/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;

import com.example.restservice.dao.admin.AdminDAO;
import com.example.restservice.model.admin.Admin;
import com.model.admin.Constant;
import com.model.admin.NotifyMessage;
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
@RequestMapping("/admin")
public class CheckAdminAPI {
    private List<Admin> list = new ArrayList<>();
    
    @GetMapping("/getAllAdmin")
    public List<Admin> findAllAdmin(){
        AdminDAO admDAO = new AdminDAO();
        return admDAO.findAllAdmin();
    }
    
    @GetMapping(path="/adminInfo")
    public Admin getAdminInfo(@RequestParam(name = "user") String username){
        AdminDAO admDAO = new AdminDAO();
        Admin adminRoot = new Admin();
        adminRoot = admDAO.findAdmin(username);
        return adminRoot;
    }
    
    @PostMapping(path="checkAdmin", consumes = MediaType.APPLICATION_JSON, 
        produces = MediaType.APPLICATION_JSON)
    public NotifyMessage checkAdmin(@RequestBody Admin adm){
        AdminDAO admDAO = new AdminDAO();
        NotifyMessage notify = new NotifyMessage();
        Admin adminRoot = new Admin();
        adminRoot = admDAO.findAdmin(adm.getUsername());
        if (adminRoot == null) {
            //|| password != adm.getPassword()
            notify.setMsg(Constant.LOGIN_FAIL);
            notify.setCode(Constant.ERROR_CODE);
        } else if (adminRoot != null) {
            if (!adminRoot.getPassword().equals(adm.getPassword()) || adm.getPassword() == "" || adm.getPassword() == null ) {
                notify.setMsg(Constant.LOGIN_FAIL);
                notify.setCode(Constant.ERROR_CODE);
            } else {
                //&& password == adm.getPassword()
                notify.setMsg(Constant.LOGIN_SUCCESS);
                notify.setCode(Constant.SUCCESS_CODE);
            }
        }
        return notify;
    }
    
}
