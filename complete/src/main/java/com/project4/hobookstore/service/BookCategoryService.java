/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore.service;

import com.project4.hobookstore.controller.BookcategoryJpaController;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.model.Bookcategory;
import com.project4.hobookstore.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author DuongPH
 */
public class BookCategoryService {

    public static void main(String[] args) {
//        BookCategoryService bcSer = new BookCategoryService();
//        BookService bSer = new BookService();
//        CategoryService cSer = new CategoryService();
//        List<Category> listC = new ArrayList<>();
//        Category c1 = new Category("Encyclopedia");
//        Category c2 = new Category("Dictionary");
//        Book book =  bSer.findBookByBId(1);
//        listC.add(c1);
//        listC.add(c2);
//        for (int i = 0; i < listC.size(); i++) {
//            Bookcategory newBC = new Bookcategory();
//            newBC.setBid(book);
//            newBC.setCid(cSer.findIdOfCategoryByName(listC.get(i).getCategoryName()));
//            bcSer.createBookCategory(newBC);
//            System.out.println("BC SUCCESSS");
//        }
        List<String> categoryName = new ArrayList<>();
        categoryName.add("AAA");
        categoryName.add("AAA");
        categoryName.add("AAA");
        System.out.println(categoryName.get(0));
    }
    
    public BookCategoryService() {
    }
    
    public List<Bookcategory> getAllBookCategory(){
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return bcJpa.findBookcategoryEntities();
    }
    
    public List<Bookcategory> getCategoryByBId(Integer bid){
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return  bcJpa.findBookCategoryByBId(bid);
    }
    
    public void createBookCategory(Bookcategory bc){
        BookcategoryJpaController bcJpa = new BookcategoryJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        bcJpa.create(bc);
    }
}
