/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.base;

/**
 *
 * @author DuongPH
 */
public class NotifyMessage {
    String msg;
    int code;

    public NotifyMessage() {
    }

    public NotifyMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public NotifyMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    
}
