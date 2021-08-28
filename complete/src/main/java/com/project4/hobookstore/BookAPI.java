/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project4.hobookstore;

import com.project4.hobookstore.service.BookService;
import com.project4.hobookstore.dto.BookDTO;
import com.project4.hobookstore.model.Book;
import com.project4.hobookstore.base.Constant;
import com.project4.hobookstore.base.NotifyMessage;
import com.project4.hobookstore.dto.ImageDTO;
import com.project4.hobookstore.model.Image;
import com.project4.hobookstore.service.ImageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author DuongPH
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/book")
public class BookAPI {

    private List<Book> list = new ArrayList<>();

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllBook")
    public List<BookDTO> findAllBooks() throws IOException {
        BookService bookSer = new BookService();
        return bookSer.findAllBookFull().stream().map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/bookInfo")
    public BookDTO getAdminInfo(@RequestParam(name = "bid") Integer bid) {
        BookService bookSer = new BookService();
        Book newBook = bookSer.findBookByBId(bid);
        BookDTO bookDTO = modelMapper.map(newBook, BookDTO.class);
        return bookDTO;
    }

    @PostMapping(path = "/create", 
//            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NotifyMessage createBook(@RequestBody Book book) {
        BookService bookSer = new BookService();
        NotifyMessage msg = new NotifyMessage();
        try {
            Book newbook = new Book(book.getTitleBook(), 
                    book.getAuthor(), 
                    book.getManufacture(), 
                    book.getPublishingCompany(), 
                    book.getYearPublish(), 
                    book.getDateSale().toString(), 
                    book.getPrice(), 
                    book.getDescription(), 
                    book.getStatus()
            );
            bookSer.createBook(newbook);
            msg.setCode(Constant.CREATE_SUCCESS);
            msg.setMsg("Create Book Success!");
        } catch (Exception e) {
            msg.setCode(Constant.CREATE_FAIL);
            msg.setMsg(e.toString());
        }
        return msg;
    }
    
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public NotifyMessage updateBook(@RequestBody BookDTO book) {
        BookService bookSer = new BookService();
        NotifyMessage msg = new NotifyMessage();
        try {
            Book updbook = new Book(book.getTitleBook(), 
                    book.getAuthor(), 
                    book.getManufacture(), 
                    book.getPublishingCompany(), 
                    book.getYearPublish(), 
                    book.getDateSale(), 
                    book.getPrice(), 
                    book.getDescription(), 
                    book.getStatus());
            bookSer.updateBook(updbook);
            msg.setCode(Constant.UPDATE_CODE_SUSCCESS);
            msg.setMsg("Update Book Success!");
        } catch (Exception e) {
            msg.setCode(Constant.UPDATE_CODE_FAIL);
            msg.setMsg(e.toString());
        }
        return msg;
    }
}
