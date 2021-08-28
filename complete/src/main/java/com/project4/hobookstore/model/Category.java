/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author DuongPH
 */
@Entity
@DynamicUpdate
@Table(name = "category", catalog = "project4", schema = "")
@XmlRootElement

@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
    , @NamedQuery(name = "Category.findByCid", query = "SELECT c FROM Category c WHERE c.cid = :cid")
    , @NamedQuery(name = "Category.updateCategory", query = "UPDATE Category c SET c.categoryName = :categoryName WHERE c.cid = :cid")
    , @NamedQuery(name = "Category.deleteCategory", query = "DELETE FROM Category c WHERE c.cid = :cid")
    , @NamedQuery(name = "Category.deleteBookCategory", query = "DELETE FROM Bookcategory c WHERE c.cid = :cid")
    , @NamedQuery(name = "Category.findByCategoryName", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryName")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cid")
    private Integer cid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "categoryName")
//    @Pattern(regexp = "^[a-zA-Z0-9]*$",message = "Invalid Category Name")
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cid", orphanRemoval = true)
    @JoinColumn(name="cid")
    private List<Bookcategory> bookcategoryList;

    public Category() {
    }

    public Category(Integer cid) {
        this.cid = cid;
    }

    public Category(Integer cid, String categoryName) {
        this.cid = cid;
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    
    
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @XmlTransient
    public List<Bookcategory> getBookcategoryList() {
        return bookcategoryList;
    }

    public void setBookcategoryList(List<Bookcategory> bookcategoryList) {
        this.bookcategoryList = bookcategoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.restservice.model.Category[ cid=" + cid + " ]";
    }

}
