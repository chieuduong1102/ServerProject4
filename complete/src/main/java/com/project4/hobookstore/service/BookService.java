/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.controller.BookJpaController;
import com.project4.hobookstore.controller.BookcategoryJpaController;
import com.project4.hobookstore.controller.ImageJpaController;
import com.project4.hobookstore.controller.OrderdetailJpaController;
import com.project4.hobookstore.controller.RatingfeedbackJpaController;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Category;
import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.model.Image;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.encode.Encode;
import com.project4.hobookstore.model.Orderdetail;
import com.project4.hobookstore.model.Ratingfeedback;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Persistence;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author DuongPH
 */
public class BookService implements Serializable {
    public static void main(String[] args) {
        BookService dao = new BookService();
        List<Book> list = new ArrayList<>();
        list = dao.findAllBookOfCategory(6);
        for (Book book : list) {
            System.out.println("Book: " + book.getBid() + " - " + book.getTitleBook());
        }
    }

    public BookService() {
    }

    public Book createBook(Book book){
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        bookJpa.createNewBook(book);
        return book;
    }
    
    public Book updateBook(Book book) {
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        if(bookJpa.findBook(book.getBid()) != null){
            bookJpa.updateBook(book);
            return book;
        } else {
            return null;
        }

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
            listImage = imgJpa.findImageByBId(listBook.get(i).getBid()).stream().map(img -> {
            String fileUri = "data:image/png;base64,"+ Encode.convertFileIntoBase64String(FileSystems.getDefault().getPath("").toAbsolutePath().toString(),
                    "\\uploads\\", img.getNameFile());
            
                return new Image(fileUri);
            }).collect(Collectors.toList());
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
            listImage = imgJpa.findImageByBId(rootBook.getBid()).stream().map(img -> {
            String fileUri = "data:image/png;base64,"+ Encode.convertFileIntoBase64String(FileSystems.getDefault().getPath("").toAbsolutePath().toString(),
                    "\\uploads\\", img.getNameFile());
            
                return new Image(fileUri);
            }).collect(Collectors.toList());
            newBook.setImageList(listImage);

        List<Ratingfeedback> listRf = new ArrayList<>();
        listRf = rfJpa.findRatingFeedbackByBId(rootBook.getBid());
        newBook.setRatingfeedbackList(listRf);

        List<Bookcategory> listBc = new ArrayList<>();
        listBc = bcJpa.findBookCategoryByBId(rootBook.getBid());
        newBook.setBookcategoryList(listBc);

        return newBook;
    }

    public Book findOneBook(String title, String author, String publishingCompany, int yearPublish ) {
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        ImageJpaController imgJpa = new ImageJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        RatingfeedbackJpaController rfJpa = new RatingfeedbackJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        Book rootBook = bookJpa.findOneBook(title, author, publishingCompany, yearPublish);
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
    
    public List<Book> findAllBookOfCategory(int cid){
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookService bookSer = new BookService();
        List<Book> list = bookSer.findAllBookFull();
        CategoryService catSer = new CategoryService();
        Category cat = catSer.findCategoryByCId(cid);
        List<Book> listBookCate = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getBookcategoryList().size(); j++) {
                if(list.get(i).getBookcategoryList().get(j).getCid().getCategoryName().equals(cat.getCategoryName())){
                    listBookCate.add(list.get(i));
                }
            }
        }
        return listBookCate;
    }
    
    public List<Book> findAllBookByPrice(String PtoP){
        Double priceStart = Double.parseDouble(PtoP.split("to")[0]);
        Double priceEnd = Double.parseDouble(PtoP.split("to")[1]);
        BookJpaController bookJpa = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        BookService bookSer = new BookService();
        List<Book> list = bookSer.findAllBookFull();
        CategoryService catSer = new CategoryService();
        List<Book> listBookP = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPrice() > priceStart && list.get(i).getPrice() < priceEnd){
                listBookP.add(list.get(i));
            }
        }
        return listBookP;
    }
    
    public void addBook(Book book) {
        BookJpaController jpaController = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        jpaController.create(book);
    }

    
}
