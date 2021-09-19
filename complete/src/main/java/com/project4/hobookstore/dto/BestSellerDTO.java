/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.dto;

/**
 *
 * @author DuongPH
 */
public class BestSellerDTO {
    private Integer bid;
    private int total;

    public BestSellerDTO(Integer bid, int total) {
        this.bid = bid;
        this.total = total;
    }

    
    
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
