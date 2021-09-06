/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project4.hobookstore.dto.ImageDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * @author DuongPH
 */
@Entity
@Table(name = "book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
    , @NamedQuery(name = "Book.findByBid", query = "SELECT b FROM Book b WHERE b.bid = :bid")
    , @NamedQuery(name = "Book.findByTitleBook", query = "SELECT b FROM Book b WHERE b.titleBook = :titleBook")
    , @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
    , @NamedQuery(name = "Book.findByManufacture", query = "SELECT b FROM Book b WHERE b.manufacture = :manufacture")
    , @NamedQuery(name = "Book.findByPublishingCompany", query = "SELECT b FROM Book b WHERE b.publishingCompany = :publishingCompany")
    , @NamedQuery(name = "Book.findOneBook", query = "SELECT b FROM Book b WHERE b.titleBook = :titleBook AND b.author = :author AND b.publishingCompany = :publishingCompany AND b.yearPublish = :yearPublish")
    , @NamedQuery(name = "Book.findByYearPublish", query = "SELECT b FROM Book b WHERE b.yearPublish = :yearPublish")
    , @NamedQuery(name = "Book.findByDateSale", query = "SELECT b FROM Book b WHERE b.dateSale = :dateSale")
    , @NamedQuery(name = "Book.findByPrice", query = "SELECT b FROM Book b WHERE b.price = :price")
    , @NamedQuery(name = "Book.updateBook", query = "UPDATE Book b"
            + " SET b.titleBook = :titleBook"
            + " , b.author = :author"
            + " , b.manufacture = :manufacture"
            + " , b.publishingCompany = :publishingCompany"
            + " , b.yearPublish = :yearPublish"
            + " , b.dateSale = :dateSale"
            + " , b.price = :price"
            + " , b.description = :description"
            + " , b.status = :status"
            + " WHERE b.bid = :bid")
    , @NamedQuery(name = "Book.findByStatus", query = "SELECT b FROM Book b WHERE b.status = :status")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bid")
    private Integer bid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titleBook")
    private String titleBook;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "manufacture")
    private String manufacture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "publishingCompany")
    private String publishingCompany;
    @Column(name = "yearPublish")
    private Integer yearPublish;
    @Size(max = 10)
    @Column(name = "dateSale")
    private String dateSale;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bid")
    private List<Ratingfeedback> ratingfeedbackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bid")
    @JsonManagedReference
    private List<Image> imageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bid")
    private List<Bookcategory> bookcategoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bid")
    private List<Orderdetail> orderdetailList;

    public Book() {
    }

    public Book(Integer bid) {
        this.bid = bid;
    }

    public Book(String titleBook, String author, String manufacture, String publishingCompany, Integer yearPublish, String dateSale, Double price, String description, Integer status) {
        this.titleBook = titleBook;
        this.author = author;
        this.manufacture = manufacture;
        this.publishingCompany = publishingCompany;
        this.yearPublish = yearPublish;
        this.dateSale = dateSale;
        this.price = price;
        this.description = description;
        this.status = status;
    }
    
    public Book(Integer bid, String titleBook, String author, String manufacture, String publishingCompany, Integer yearPublish, String dateSale, Double price, String description, Integer status) {
        this.bid = bid;
        this.titleBook = titleBook;
        this.author = author;
        this.manufacture = manufacture;
        this.publishingCompany = publishingCompany;
        this.yearPublish = yearPublish;
        this.dateSale = dateSale;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public Book(Integer bid, String titleBook, String author, String manufacture, String publishingCompany, Integer yearPublish, String dateSale, Double price, String description, Integer status, List<Ratingfeedback> ratingfeedbackList, List<Image> imageList, List<Bookcategory> bookcategoryList, List<Orderdetail> orderdetailList) {
        this.bid = bid;
        this.titleBook = titleBook;
        this.author = author;
        this.manufacture = manufacture;
        this.publishingCompany = publishingCompany;
        this.yearPublish = yearPublish;
        this.dateSale = dateSale;
        this.price = price;
        this.description = description;
        this.status = status;
        this.ratingfeedbackList = ratingfeedbackList;
        this.imageList = imageList;
        this.bookcategoryList = bookcategoryList;
        this.orderdetailList = orderdetailList;
    }

    public Book(String titleBook, String author, String manufacture, String publishingCompany, Integer yearPublish, String dateSale, Double price, String description, Integer status, List<Ratingfeedback> ratingfeedbackList, List<Image> imageList) {
                this.titleBook = titleBook;
        this.author = author;
        this.manufacture = manufacture;
        this.publishingCompany = publishingCompany;
        this.yearPublish = yearPublish;
        this.dateSale = dateSale;
        this.price = price;
        this.description = description;
        this.status = status;
        this.ratingfeedbackList = ratingfeedbackList;
        this.imageList = imageList;
    }

    
   

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public Integer getYearPublish() {
        return yearPublish;
    }

    public void setYearPublish(Integer yearPublish) {
        this.yearPublish = yearPublish;
    }

    public String getDateSale() {
        return dateSale;
    }

    public void setDateSale(String dateSale) {
        this.dateSale = dateSale;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public List<Ratingfeedback> getRatingfeedbackList() {
        return ratingfeedbackList;
    }

    public void setRatingfeedbackList(List<Ratingfeedback> ratingfeedbackList) {
        this.ratingfeedbackList = ratingfeedbackList;
    }

    @XmlTransient
    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    @XmlTransient
    public List<Bookcategory> getBookcategoryList() {
        return bookcategoryList;
    }

    public void setBookcategoryList(List<Bookcategory> bookcategoryList) {
        this.bookcategoryList = bookcategoryList;
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
        hash += (bid != null ? bid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bid == null && other.bid != null) || (this.bid != null && !this.bid.equals(other.bid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.restservice.model.Book[ bid=" + bid + " ]";
    }
    
}
