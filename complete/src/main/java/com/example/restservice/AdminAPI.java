/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;

import com.example.restservice.dao.AdminDAO;
import com.example.restservice.encode.Encode;
import com.example.restservice.model.Admin;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class AdminAPI {

    private List<Admin> list = new ArrayList<>();

    @GetMapping("/getAllAdmin")
    public List<Admin> findAllAdmin() {
        AdminDAO admDAO = new AdminDAO();
        return admDAO.findAllAdmin();
    }

    @GetMapping(path = "/adminInfo")
    public Admin getAdminInfo(@RequestParam(name = "admin") String username) {
        AdminDAO admDAO = new AdminDAO();
        Admin adminRoot = new Admin();
        adminRoot = admDAO.findAdmin(username);
        return adminRoot;
    }

    @GetMapping(path = "/findAdminByName")
    public ResponseEntity<List<Admin>> findAdminByName(@RequestParam(name = "name") String name) {
        AdminDAO admDAO = new AdminDAO();
        List<Admin> adminRoot = new ArrayList<>();
        adminRoot = admDAO.findAdminByName(name);
        if(adminRoot.size()>0)
            return new ResponseEntity<>(adminRoot, HttpStatus.OK);
        else
            return new ResponseEntity(adminRoot,HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "checkAdmin", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage checkAdmin(@RequestBody Admin adm) {
        AdminDAO admDAO = new AdminDAO();
        NotifyMessage notify = new NotifyMessage();
        Admin adminRoot = new Admin();
        adminRoot = admDAO.findAdmin(adm.getUsername());
        if (adminRoot == null) {
            //|| password != adm.getPassword()
            notify.setMsg(Constant.LOGIN_FAIL);
            notify.setCode(Constant.LOGIN_CODE_FAIL);
        } else if (adminRoot != null) {
            if (!adminRoot.getPassword().equals(Encode.getSHAHash(adm.getPassword())) || adm.getPassword() == "" || adm.getPassword() == null) {
                notify.setMsg(Constant.LOGIN_FAIL);
                notify.setCode(Constant.LOGIN_CODE_FAIL);
            } else {
                //&& password == adm.getPassword()
                notify.setMsg(Constant.LOGIN_SUCCESS);
                notify.setCode(Constant.LOGIN_CODE_SUSCCESS);
            }
        }
        return notify;
    }

    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage createAdminAccount(@RequestBody Admin adm) {
        AdminDAO admDAO = new AdminDAO();
        Admin newAdmin = new Admin();
        NotifyMessage msg = new NotifyMessage();
        try {
            newAdmin.setFullname(adm.getFullname());
            newAdmin.setUsername(adm.getUsername());
            newAdmin.setEmail(adm.getEmail());
            newAdmin.setPhonenumber(adm.getPhonenumber());
            newAdmin.setPassword(Encode.getSHAHash(adm.getPassword()));
            msg = admDAO.createAdmin(newAdmin);
        } catch (Exception e) {

        }
        return msg;
    }

    @PostMapping(path = "update", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage updateAdminAccount(@RequestBody Admin adm) {
        AdminDAO admDAO = new AdminDAO();
        Admin updAdmin = new Admin();
        NotifyMessage msg = new NotifyMessage();
        try {
            updAdmin.setFullname(adm.getFullname());
            updAdmin.setUsername(adm.getUsername());
            updAdmin.setEmail(adm.getEmail());
            updAdmin.setPhonenumber(adm.getPhonenumber());
            updAdmin.setPassword(Encode.getSHAHash(adm.getPassword()));
            msg = admDAO.updateAdmin(updAdmin);
        } catch (Exception e) {

        }
        return msg;
    }

    @PostMapping(path = "delete", consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public NotifyMessage deleteAdminAccount(@RequestBody Admin adm) {
        AdminDAO admDAO = new AdminDAO();
        Admin delAdmin = new Admin();
        NotifyMessage msg = new NotifyMessage();
        try {
            delAdmin.setFullname(adm.getFullname());
            delAdmin.setUsername(adm.getUsername());
            delAdmin.setEmail(adm.getEmail());
            delAdmin.setPhonenumber(adm.getPhonenumber());
            delAdmin.setPassword(Encode.getSHAHash(adm.getPassword()));
            msg = admDAO.deleteAdmin(delAdmin);
        } catch (Exception e) {

        }
        return msg;
    }
}
