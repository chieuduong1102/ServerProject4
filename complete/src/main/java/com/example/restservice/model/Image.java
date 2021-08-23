/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author T480s
 */
@Entity
@Table(name = "image", catalog = "project4", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")
    , @NamedQuery(name = "Image.findByIid", query = "SELECT i FROM Image i WHERE i.iid = :iid")
    , @NamedQuery(name = "Image.findByNameFile", query = "SELECT i FROM Image i WHERE i.nameFile = :nameFile")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iid")
    private Integer iid;
    @Size(max = 255)
    @Column(name = "nameFile")
    private String nameFile;
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Book bid;

    public Image() {
    }

    public Image(Integer iid) {
        this.iid = iid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public Book getBid() {
//        this.setBid(null);
        return bid;
    }

    public void setBid(Book bid) {
        this.bid = bid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iid != null ? iid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.iid == null && other.iid != null) || (this.iid != null && !this.iid.equals(other.iid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.restservice.model.Image[ iid=" + iid + " ]";
    }
    
}
