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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author T480s
 */
@Entity
@Table(name = "orderdetail", catalog = "project4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o")
    , @NamedQuery(name = "Orderdetail.findById", query = "SELECT o FROM Orderdetail o WHERE o.id = :id")
    , @NamedQuery(name = "Orderdetail.findByAmount", query = "SELECT o FROM Orderdetail o WHERE o.amount = :amount")})
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private Order1 oid;
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    @ManyToOne(optional = false)
    private Book bid;

    public Orderdetail() {
    }

    public Orderdetail(Integer id) {
        this.id = id;
    }

    public Orderdetail(Integer id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Order1 getOid() {
        return oid;
    }

    public void setOid(Order1 oid) {
        this.oid = oid;
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
        if (!(object instanceof Orderdetail)) {
            return false;
        }
        Orderdetail other = (Orderdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.restservice.model.Orderdetail[ id=" + id + " ]";
    }
    
}
