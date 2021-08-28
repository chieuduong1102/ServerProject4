/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.dto;

import lombok.Data;

/**
 *
 * @author DuongPH
 */
@Data
public class UserDTO {
    private String username;
    private String fullname;
    private String email;
    private String phonenumber;
    private String address;
    private String password;
    
    
}
