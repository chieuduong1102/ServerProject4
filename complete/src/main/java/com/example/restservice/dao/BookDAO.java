/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.dao;

import com.example.restservice.controller.BookJpaController;
import com.example.restservice.controller.CategoryJpaController;
import com.example.restservice.dto.BookDTO;
import com.example.restservice.model.Book;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.Tuple;

/**
 *
 * @author T480s
 */
public class BookDAO implements Serializable{
    
    public BookDAO() {
    }

    public List<Book> findAll() {
        BookJpaController jpaController = new BookJpaController(Persistence.createEntityManagerFactory("ServerRESTfulAPIPU"));
        return jpaController.getBookList();
    }

    
}
