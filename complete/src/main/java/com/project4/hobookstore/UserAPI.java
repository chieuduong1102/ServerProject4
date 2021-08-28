/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;


import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.UserDTO;
import com.project4.hobookstore.encode.Encode;
import com.project4.hobookstore.model.Order1;
import com.project4.hobookstore.model.Ratingfeedback;
import com.project4.hobookstore.model.User;
import com.project4.hobookstore.service.UserService;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/user")
public class UserAPI {
    private List<User> list = new ArrayList<>();
    @Autowired
    private ModelMapper modelMapper;
        
    @GetMapping("/getAllUser")
    public List<UserDTO> findAllUser(){
        UserService userSer = new UserService();
        return userSer.findAllUser().stream().map(book -> modelMapper.map(book, UserDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping(path="/userInfo")
    public UserDTO getUserInfo(@RequestParam(name = "user") String username){
        UserService userSer = new UserService();
        User userRoot = new User();
        userRoot = userSer.findUser(username);
        UserDTO userDTO = modelMapper.map(userRoot, UserDTO.class);
        return userDTO;
    }

    @PostMapping(path="checkUser", consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public NotifyMessage checkUser(@RequestBody User user){
        UserService userSer = new UserService();
        NotifyMessage notify = new NotifyMessage();
        User userRoot = new User();
        userRoot = userSer.findUser(user.getUsername());
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
    
    @PostMapping(path="create", consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public NotifyMessage createUserAccount(@RequestBody UserDTO user){
        UserService userSer = new UserService();
        User newUser = new User();
        NotifyMessage msg = new NotifyMessage();
        try {
            newUser.setFullname(user.getFullname());
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setAddress(user.getAddress());
            newUser.setPhonenumber(user.getPhonenumber());
            newUser.setPassword(Encode.getSHAHash(user.getPassword()));
            msg = userSer.createUser(newUser);
        } catch (Exception e) {
            
        }
        return msg;
    }
    
    @PostMapping(path="update", consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public NotifyMessage updateUserAccount(@RequestBody UserDTO user){
        UserService userSer = new UserService();
        User updUser = new User();
        NotifyMessage msg = new NotifyMessage();
        try {
            updUser.setFullname(user.getFullname());
            updUser.setUsername(user.getUsername());
            updUser.setEmail(user.getEmail());
            updUser.setAddress(user.getAddress());
            updUser.setPhonenumber(user.getPhonenumber());
            updUser.setPassword(Encode.getSHAHash(user.getPassword()));
            List<Ratingfeedback> listRf = new ArrayList<>();
            updUser.setRatingfeedbackList(listRf);
            List<Order1> listOd = new ArrayList<>();
            updUser.setOrder1List(listOd);
            msg = userSer.updateUser(updUser);
        } catch (Exception e) {
            
        }
        return msg;
    }
    
    @PostMapping(path="delete", consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public NotifyMessage deleteUserAccount(@RequestBody UserDTO user){
        UserService userSer = new UserService();
        User delUser = new User();
        NotifyMessage msg = new NotifyMessage();
        try {
            delUser.setFullname(user.getFullname());
            delUser.setUsername(user.getUsername());
            delUser.setEmail(user.getEmail());
            delUser.setAddress(user.getAddress());
            delUser.setPhonenumber(user.getPhonenumber());
            delUser.setPassword(Encode.getSHAHash(user.getPassword()));
            msg = userSer.deleteUser(delUser);
        } catch (Exception e) {
            
        }
        return msg;
    }
}
