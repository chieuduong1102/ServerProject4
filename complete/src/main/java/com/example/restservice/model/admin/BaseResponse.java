/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.admin;

/**
 *
 * @author DuongPH
 */
public class BaseResponse<Type> {
    public Type data;

    public BaseResponse() {
    }

    public BaseResponse(Type data) {
        this.data = data;
    }

    public Type getData() {
        return data;
    }

    public void setData(Type data) {
        this.data = data;
    }
    
}
