/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.dao;

import com.example.restservice.controller.BookJpaController;
import com.example.restservice.controller.BookcategoryJpaController;
import com.example.restservice.controller.ImageJpaController;
import com.example.restservice.controller.OrderdetailJpaController;
import com.example.restservice.controller.RatingfeedbackJpaController;
import com.example.restservice.model.Book;
import com.example.restservice.model.Bookcategory;
import com.example.restservice.model.Category;
import com.example.restservice.model.Image;
import com.example.restservice.model.Orderdetail;
import com.example.restservice.model.Ratingfeedback;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author DuongPH
 */
public class BookDAO implements Serializable {

    public static void main(String[] args) {
        BookDAO dao = new BookDAO();
        List<Book> list = new ArrayList<>();
        list = dao.findAllBookFull();
        for (Book book : list) {
            System.out.println("Book: " + book.getImageList().get(0));
        }
    }

    public BookDAO() {
    }

    public List<Book> findAll() {
        BookJpaController jpaController = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.getBookList();
    }

    public List<Book> findAllBookFull() {
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        RatingfeedbackJpaController rfJpa = new RatingfeedbackJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        List<Book> listBoolFullInfo = new ArrayList<>();
        List<Book> listBook = new ArrayList<>();
        listBook = bookJpa.findBookEntities();
        for (int i = 0; i < listBook.size(); i++) {
            Book newBook = new Book();
            newBook.setBid(listBook.get(i).getBid());
            newBook.setTitleBook(listBook.get(i).getTitleBook());
            newBook.setAuthor(listBook.get(i).getAuthor());
            newBook.setManufacture(listBook.get(i).getManufacture());
            newBook.setPublishingCompany(listBook.get(i).getPublishingCompany());
            newBook.setYearPublish(listBook.get(i).getYearPublish());
            newBook.setDateSale(listBook.get(i).getDateSale());
            newBook.setPrice(listBook.get(i).getPrice());
            newBook.setDescription(listBook.get(i).getDescription());
            newBook.setStatus(listBook.get(i).getStatus());

            List<Image> listImage = new ArrayList<>();
            listImage = imgJpa.findImageByBId(listBook.get(i).getBid());
            newBook.setImageList(listImage);

            List<Ratingfeedback> listRf = new ArrayList<>();
            listRf = rfJpa.findRatingFeedbackByBId(listBook.get(i).getBid());
            newBook.setRatingfeedbackList(listRf);

            List<Bookcategory> listBc = new ArrayList<>();
            listBc = bcJpa.findBookCategoryByBId(listBook.get(i).getBid());
            newBook.setBookcategoryList(listBc);

            listBoolFullInfo.add(newBook);
        }

        return listBoolFullInfo;
    }

    public Book findBookByBId(Integer id) {
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        RatingfeedbackJpaController rfJpa = new RatingfeedbackJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Book rootBook = bookJpa.findBook(id);
        Book newBook = new Book();
        newBook.setBid(rootBook.getBid());
        newBook.setTitleBook(rootBook.getTitleBook());
        newBook.setAuthor(rootBook.getAuthor());
        newBook.setManufacture(rootBook.getManufacture());
        newBook.setPublishingCompany(rootBook.getPublishingCompany());
        newBook.setYearPublish(rootBook.getYearPublish());
        newBook.setDateSale(rootBook.getDateSale());
        newBook.setPrice(rootBook.getPrice());
        newBook.setDescription(rootBook.getDescription());
        newBook.setStatus(rootBook.getStatus());

        List<Image> listImage = new ArrayList<>();
        listImage = imgJpa.findImageByBId(rootBook.getBid());
        newBook.setImageList(listImage);

        List<Ratingfeedback> listRf = new ArrayList<>();
        listRf = rfJpa.findRatingFeedbackByBId(rootBook.getBid());
        newBook.setRatingfeedbackList(listRf);

        List<Bookcategory> listBc = new ArrayList<>();
        listBc = bcJpa.findBookCategoryByBId(rootBook.getBid());
        newBook.setBookcategoryList(listBc);

        return newBook;
    }

}
