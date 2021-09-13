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
public class RatingfeedbackDTO {

    private Integer scoreRate;
    private String feedback;
    private String username;
    private Integer bid;

    public Integer getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(Integer scoreRate) {
        this.scoreRate = scoreRate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
}
