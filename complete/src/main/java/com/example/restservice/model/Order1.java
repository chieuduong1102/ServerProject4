/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author T480s
 */
@Entity
@Table(name = "order", catalog = "project4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o")
    , @NamedQuery(name = "Order1.findByOid", query = "SELECT o FROM Order1 o WHERE o.oid = :oid")
    , @NamedQuery(name = "Order1.findByTimeOrder", query = "SELECT o FROM Order1 o WHERE o.timeOrder = :timeOrder")
    , @NamedQuery(name = "Order1.findByDeliveryAddress", query = "SELECT o FROM Order1 o WHERE o.deliveryAddress = :deliveryAddress")
    , @NamedQuery(name = "Order1.findByTotalPrice", query = "SELECT o FROM Order1 o WHERE o.totalPrice = :totalPrice")
    , @NamedQuery(name = "Order1.findByStatus", query = "SELECT o FROM Order1 o WHERE o.status = :status")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "timeOrder")
    private String timeOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "deliveryAddress")
    private String deliveryAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalPrice")
    private double totalPrice;
    @Lob
    @Size(max = 65535)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oid")
    private List<Orderdetail> orderdetailList;

    public Order1() {
    }

    public Order1(Integer oid) {
        this.oid = oid;
    }

    public Order1(Integer oid, String timeOrder, String deliveryAddress, double totalPrice, int status) {
        this.oid = oid;
        this.timeOrder = timeOrder;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @XmlTransient
    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.restservice.model.Order1[ oid=" + oid + " ]";
    }
    
}
