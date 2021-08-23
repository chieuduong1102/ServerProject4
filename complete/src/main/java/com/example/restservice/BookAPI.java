/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;

import com.example.restservice.dao.BookDAO;
import com.example.restservice.dto.BookDTO;
import com.example.restservice.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author T480s
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/book")
public class BookAPI {
    private List<Book> list = new ArrayList<>();
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/getAllBooks")
    public List<BookDTO> findAllBooks() {
        BookDAO bookDAO = new BookDAO();
       return bookDAO.findAll().stream().map(book -> modelMapper.map(book, BookDTO.class))
				.collect(Collectors.toList());

    }
}
