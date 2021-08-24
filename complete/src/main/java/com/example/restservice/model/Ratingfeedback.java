/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DuongPH
 */
@Entity
@Table(name = "ratingfeedback", catalog = "project4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ratingfeedback.findAll", query = "SELECT r FROM Ratingfeedback r")
    , @NamedQuery(name = "Ratingfeedback.findById", query = "SELECT r FROM Ratingfeedback r WHERE r.id = :id")
    , @NamedQuery(name = "Ratingfeedback.findByBId", query = "SELECT r FROM Ratingfeedback r WHERE r.bid.bid = :bid")
    , @NamedQuery(name = "Ratingfeedback.findByScoreRate", query = "SELECT r FROM Ratingfeedback r WHERE r.scoreRate = :scoreRate")})
public class Ratingfeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "scoreRate")
    private Integer scoreRate;
    @Lob
    @Size(max = 65535)
    @Column(name = "feedback")
    private String feedback;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    @ManyToOne(optional = false)
    private Book bid;

    public Ratingfeedback() {
    }

    public Ratingfeedback(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Book getBid() {
        return bid;
    }

    public void setBid(Book bid) {
        this.bid = bid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ratingfeedback)) {
            return false;
        }
        Ratingfeedback other = (Ratingfeedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.restservice.model.Ratingfeedback[ id=" + id + " ]";
    }
    
}
